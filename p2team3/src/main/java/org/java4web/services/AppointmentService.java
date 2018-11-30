package org.java4web.services;

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
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AppointmentService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final SpecialtyRepository specialtyRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository,
                              DoctorRepository doctorRepository, SpecialtyRepository specialtyRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.specialtyRepository = specialtyRepository;
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

            return createMJVforGetAppointment(appointmentRepository.save(entityAppointment));
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
                    return createMJVforGetAppointments((appointmentRepository.findBySpecialtyId(specialty.get().getId())));
                } else {
                    throw new SpecialtyNotFoundException(specialtyIdStr);
                }
            } else if (fromDate != null && toDate != null) {
                return createMJVforGetAppointments((appointmentRepository.findBySpecialtyIdAndDateTimeBetween(specialtyId, fromDate, toDate)));
            } else if(fromDate!= null) {
                return createMJVforGetAppointments((appointmentRepository.findBySpecialtyIdAndDateTimeAfter(specialtyId, fromDate)));
            }
            else{
                return createMJVforGetAppointments((appointmentRepository.findBySpecialtyIdAndDateTimeBefore(specialtyId, toDate)));
            }
        } else {
            return getAppointments(fromDate, toDate);
        }
    }

    public MappingJacksonValue getDoctorAppointments(String description, String dateFrom, String dateTo) {

        Date fromDate = Utils.dateFormatParse(dateFrom);
        Date toDate = Utils.dateFormatParse(dateTo);

        if (description != null){

            //TODO: With elastic search

            return null;
        } else {
            return getAppointments(fromDate, toDate);
        }
    }

    public MappingJacksonValue getAppointments(Date fromDate, Date toDate){

        if (fromDate != null && toDate != null) {
            return createMJVforGetAppointments(appointmentRepository.findByDateTimeBetween(fromDate, toDate));
        } else if(fromDate != null) {
            return createMJVforGetAppointments(appointmentRepository.findByDateTimeAfter(fromDate));
        }
        else if(toDate != null){
            return createMJVforGetAppointments(appointmentRepository.findByDateTimeBefore(toDate));
        }
        return createMJVforGetAppointments(appointmentRepository.findAll());
    }

    MappingJacksonValue createMJVforGetAppointments(List<Appointment> appointments) {
        return applyMJVAppointmentsFilters(new MappingJacksonValue(appointments));
    }


    MappingJacksonValue createMJVforGetAppointment(Appointment appointment) {
        return applyMJVAppointmentsFilters(new MappingJacksonValue(appointment));
    }

    MappingJacksonValue applyMJVAppointmentsFilters(MappingJacksonValue wrapper) {
        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (wrapper != null) {
            userDetails.getUser().setFiltersForGetAppointments(wrapper);
        }
        return wrapper;
    }

    @PutMapping("/appointments/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody @Valid AppointmentDto
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

            return appointmentRepository.save(appointmentToUpdate);
        } else {
            throw new AppointmentCannotBeUpdated();
        }

    }

    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(id));

    }

    public void deleteAppointmentById(@PathVariable Long id) {

        getAppointmentById(id);
        appointmentRepository.deleteById(id);

    }
}

