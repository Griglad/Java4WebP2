package org.java4web.services;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.java4web.exceptions.AppointmentCannotBeUpdated;
import org.java4web.exceptions.AppointmentNotFoundException;
import org.java4web.exceptions.DoctorNotFoundException;
import org.java4web.exceptions.SpecialtyNotFoundException;
import org.java4web.model.*;
import org.java4web.repositories.AppointmentRepository;
import org.java4web.repositories.DoctorRepository;
import org.java4web.repositories.PatientRepository;
import org.java4web.repositories.SpecialtyRepository;
import org.java4web.security.CustomUserDetails;
import org.java4web.utils.AppointmentDto;
import org.java4web.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AppointmentService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final SpecialtyRepository specialtyRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository,
                              DoctorRepository doctorRepository, SpecialtyRepository specialtyRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.specialtyRepository = specialtyRepository;
    }

    public MappingJacksonValue getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(id));
        return createMJVAppointment(appointment, true);
    }

    public MappingJacksonValue newAppointment(@Valid AppointmentDto appointmentDto, Principal principal) {
        Date appointmentDate = Utils.dateFormatParse(appointmentDto.getDate());

        if (!appointmentDate.before(Utils.getToday())) {

            Appointment entityAppointment = new Appointment();

            Patient patient = patientRepository.findByUsername(principal.getName());

            entityAppointment.setPatient(patient);
            Optional<Doctor> doctorOptional = doctorRepository.findById(appointmentDto.getDoctorId());
            if (doctorOptional == null) {
                throw new DoctorNotFoundException(appointmentDto.getDoctorId().toString());
            }

            Doctor doctor = doctorOptional.get();
            entityAppointment.setDoctor(doctor);
            entityAppointment.setDateTime(Utils.dateFormatParse(appointmentDto.getDate()));
            entityAppointment.setDescr(appointmentDto.getDescr());
            entityAppointment.setNotes(appointmentDto.getNotes());

            return createMJVAppointment(appointmentRepository.save(entityAppointment), false);
        }

        return null;
    }

    public MappingJacksonValue getPatientAppointments(String specialtyIdStr, String dateFrom, String dateTo) throws SpecialtyNotFoundException {

        Date fromDate = Utils.dateFormatParse(dateFrom);
        Date toDate = Utils.dateFormatParse(dateTo);

        if (specialtyIdStr != null) {
            Long specialtyId = Long.valueOf(specialtyIdStr);
            Optional<Specialty> specialty = specialtyRepository.findById(specialtyId);
            if (fromDate == null && toDate == null) {
                //TODO: Catch Long parsing.
                if (specialty.isPresent()) {
                    return createMJVAppointments((appointmentRepository.findBySpecialtyId(specialty.get().getId())), false);
                } else {
                    throw new SpecialtyNotFoundException(specialtyIdStr);
                }
            } else if (fromDate != null && toDate != null) {
                return createMJVAppointments((appointmentRepository.findBySpecialtyIdAndDateTimeBetween(specialtyId, fromDate, toDate)), false);
            } else if(fromDate!= null) {
                return createMJVAppointments((appointmentRepository.findBySpecialtyIdAndDateTimeAfter(specialtyId, fromDate)), false);
            }
            else{
                return createMJVAppointments((appointmentRepository.findBySpecialtyIdAndDateTimeBefore(specialtyId, toDate)), false);
            }
        } else {
            return getAppointments(fromDate, toDate);
        }
    }

    public MappingJacksonValue getDoctorAppointments(String descr,String dateFrom,String dateTo) {

        Date fromDate = Utils.dateFormatParse(dateFrom);
        Date toDate = Utils.dateFormatParse(dateTo);

        if(descr!=null){
            if(fromDate == null && toDate == null){
                return createMJVAppointments(appointmentRepository.findByDescription(descr), false);
            } else if(fromDate != null && toDate != null){
                return createMJVAppointments(appointmentRepository.findByDescriptionAndDateTimeBetween(descr,fromDate,toDate), false);
            } else if(fromDate != null){
                return createMJVAppointments(appointmentRepository.findByDescriptionAndDateTimeAfter(descr,fromDate), false);
            } else{
                return createMJVAppointments(appointmentRepository.findByDescriptionAndDateTimeBefore(descr,toDate), false);
            }
        }else {
            return getAppointments(fromDate, toDate);
        }
    }

    public MappingJacksonValue getAppointments(Date fromDate, Date toDate){

        if (fromDate != null && toDate != null) {
            return createMJVAppointments(appointmentRepository.findByDateTimeBetween(fromDate, toDate), false);
        } else if(fromDate != null) {
            return createMJVAppointments(appointmentRepository.findByDateTimeAfter(fromDate), false);
        }
        else if(toDate != null){
            return createMJVAppointments(appointmentRepository.findByDateTimeBefore(toDate), false);
        }
        return createMJVAppointments(appointmentRepository.findAll(), false);
    }

    public MappingJacksonValue updateAppointment(@PathVariable Long id, @RequestBody @Valid AppointmentDto
            appointmentDtoToUpdate, Principal principal) {

        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);


        Date newDate = Utils.dateFormatParse(appointmentDtoToUpdate.getDate());
        if (!newDate.before(Utils.getToday())) {

            Appointment appointmentToUpdate = optionalAppointment.get();


            String changedDescription = appointmentDtoToUpdate.getDescr();
            String changedNotes = appointmentDtoToUpdate.getNotes();
            appointmentToUpdate.setDescr(changedDescription);
            appointmentToUpdate.setNotes(changedNotes);


            appointmentToUpdate.setDateTime(newDate);
            Appointment appointment = appointmentRepository.save(appointmentToUpdate);
            return createMJVAppointment(appointment, true);
        } else {
            throw new AppointmentCannotBeUpdated();
        }

    }

    public void deleteAppointmentById(@PathVariable Long id) {

        getAppointmentById(id);
        appointmentRepository.deleteById(id);
    }

    MappingJacksonValue createMJVAppointments(List<Appointment> appointments, boolean defaultFilters) {
        if(defaultFilters)
            return applyMJVAppointmentsCustomFilters(new MappingJacksonValue(appointments));
        else{
            return applyMJVAppointmentsCustomFilters(new MappingJacksonValue(appointments));
        }
    }


    MappingJacksonValue createMJVAppointment(Appointment appointment, boolean defaultFilters) {
        if(defaultFilters)
            return applyMJVAppointmentDefaultFilters(new MappingJacksonValue(appointment));
        else{
            return applyMJVAppointmentsCustomFilters(new MappingJacksonValue(appointment));
        }
    }

    MappingJacksonValue applyMJVAppointmentDefaultFilters(MappingJacksonValue wrapper){
        wrapper.setFilters(new SimpleFilterProvider()
                .addFilter("AppointmentFilter", SimpleBeanPropertyFilter.serializeAll()));
        return wrapper;
    }

    MappingJacksonValue applyMJVAppointmentsCustomFilters(MappingJacksonValue wrapper) {
        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (wrapper != null) {
            userDetails.getUser().setFiltersForGetAppointments(wrapper);
        }
        return wrapper;
    }

}

