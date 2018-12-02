package org.java4web.services;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.java4web.exceptions.AppointmentCannotBeUpdated;
import org.java4web.exceptions.AppointmentNotFoundException;
import org.java4web.exceptions.SpecialtyNotFoundException;
import org.java4web.model.*;
import org.java4web.repositories.AppointmentRepository;
import org.java4web.security.CustomUserDetails;
import org.java4web.utils.AppointmentDto;
import org.java4web.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AppointmentRepository appointmentRepository;
    private final SpecialtyService specialtyService;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, PatientService patientService,
                              DoctorService doctorService, SpecialtyService specialtyService) {
        this.appointmentRepository = appointmentRepository;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.specialtyService = specialtyService;
    }

    @PostAuthorize("hasRole('ROLE_ADMIN') " +
            "or (hasRole('ROLE_PATIENT') and returnObject.patient.username == authentication.principal.username) " +
            "or (hasRole('ROLE_DOCTOR') and returnObject.doctor.username == authentication.principal.username)")
    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(id));
    }

    @PreFilter("hasRole('ROLE_ADMIN') " +
            "or (hasRole('ROLE_PATIENT') and filterObject.patient.username == authentication.principal.username)")
    public Appointment saveAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    @Secured({"ROLE_PATIENT"})
    public Appointment newAppointment(AppointmentDto appointmentDto, Principal principal) {
        Date appointmentDate = Utils.dateFormatParse(appointmentDto.getDate());

        if (!appointmentDate.before(Utils.getToday())) {

            Appointment entityAppointment = new Appointment();

            Patient patient = patientService.getPatientByUsername(principal.getName());
            entityAppointment.setPatient(patient);

            Doctor doctor = doctorService.getDoctor(appointmentDto.getDoctorId());
            entityAppointment.setDoctor(doctor);

            entityAppointment.setDateTime(Utils.dateFormatParse(appointmentDto.getDate()));
            entityAppointment.setDescr(appointmentDto.getDescr());
            entityAppointment.setNotes(appointmentDto.getNotes());

            return appointmentRepository.save(entityAppointment);
        }

        return null;
    }

    @PostFilter("hasRole('ROLE_ADMIN') " +
            "or (hasRole('ROLE_PATIENT') and filterObject.patient.username == authentication.principal.username)")
    public List<Appointment> getPatientAppointments(String specialtyIdStr, String dateFrom, String dateTo) throws SpecialtyNotFoundException {

        Date fromDate = Utils.dateFormatParse(dateFrom);
        Date toDate = Utils.dateFormatParse(dateTo);

        if (specialtyIdStr != null) {
            Long specialtyId = Long.valueOf(specialtyIdStr);
            Specialty specialty = specialtyService.getSpecialty(specialtyId);
            if (fromDate == null && toDate == null) {
                return appointmentRepository.findBySpecialtyId(specialty.getId());
            } else if (fromDate != null && toDate != null) {
                return appointmentRepository.findBySpecialtyIdAndDateTimeBetween(specialtyId, fromDate, toDate);
            } else if(fromDate!= null) {
                return appointmentRepository.findBySpecialtyIdAndDateTimeAfter(specialtyId, fromDate);
            }
            else{
                return appointmentRepository.findBySpecialtyIdAndDateTimeBefore(specialtyId, toDate);
            }
        } else {
            return getAppointments(fromDate, toDate);
        }
    }

    @PostFilter("hasRole('ROLE_ADMIN') " +
            "or (hasRole('ROLE_PATIENT') and filterObject.patient.username == authentication.principal.username) " +
            "or (hasRole('ROLE_DOCTOR') and filterObject.doctor.username == authentication.principal.username)")
    public List<Appointment> getDoctorAppointments(String descr,String dateFrom,String dateTo) {

        Date fromDate = Utils.dateFormatParse(dateFrom);
        Date toDate = Utils.dateFormatParse(dateTo);

        if(descr!=null){
            if(fromDate == null && toDate == null){
                return appointmentRepository.findByDescription(descr);
            } else if(fromDate != null && toDate != null){
                return appointmentRepository.findByDescriptionAndDateTimeBetween(descr,fromDate,toDate);
            } else if(fromDate != null){
                return appointmentRepository.findByDescriptionAndDateTimeAfter(descr,fromDate);
            } else{
                return appointmentRepository.findByDescriptionAndDateTimeBefore(descr,toDate);
            }
        }else {
            return getAppointments(fromDate, toDate);
        }
    }

    @PostFilter("hasRole('ROLE_ADMIN') " +
            "or (hasRole('ROLE_DOCTOR') and filterObject.doctor.username == authentication.principal.username)")
    public List<Appointment> getAppointments(Date fromDate, Date toDate){

        if (fromDate != null && toDate != null) {
            appointmentRepository.findByDateTimeBetween(fromDate, toDate);
        } else if(fromDate != null) {
            return appointmentRepository.findByDateTimeAfter(fromDate);
        }
        else if(toDate != null){
            return appointmentRepository.findByDateTimeBefore(toDate);
        }
        return appointmentRepository.findAll();
    }

    @Secured({"ROLE_PATIENT"})
    public Appointment updateAppointment(Long id, AppointmentDto appointmentDtoToUpdate, Principal principal) {

        Appointment appointmentToUpdate = getAppointment(id);

        Date newDate = Utils.dateFormatParse(appointmentDtoToUpdate.getDate());
        if (!newDate.before(Utils.getToday())) {
            String changedDescription = appointmentDtoToUpdate.getDescr();
            String changedNotes = appointmentDtoToUpdate.getNotes();

            appointmentToUpdate.setDescr(changedDescription);
            appointmentToUpdate.setNotes(changedNotes);
            appointmentToUpdate.setDateTime(newDate);

            return saveAppointment(appointmentToUpdate);
        } else {
            throw new AppointmentCannotBeUpdated(id);
        }
    }

    public MappingJacksonValue createMJVAppointments(List<Appointment> appointments, boolean defaultFilters) {
        if(defaultFilters)
            return applyMJVAppointmentsCustomFilters(new MappingJacksonValue(appointments));
        else{
            return applyMJVAppointmentsCustomFilters(new MappingJacksonValue(appointments));
        }
    }


    public MappingJacksonValue createMJVAppointment(Appointment appointment, boolean defaultFilters) {
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

    @PreAuthorize("hasRole('ROLE_ADMIN') " +
            "or (hasRole('ROLE_PATIENT') and #appointment.patient.username == authentication.principal.username)")
    public void deleteAppointment(Appointment appointment){
        appointmentRepository.delete(appointment);
    }
}

