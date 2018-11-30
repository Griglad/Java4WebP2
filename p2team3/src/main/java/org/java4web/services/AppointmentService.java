package org.java4web.services;

import net.bytebuddy.implementation.bytecode.Throw;
import org.aspectj.util.UtilClassLoader;
import org.java4web.exceptions.AppointmentCannotBeUpgrated;
import org.java4web.exceptions.AppointmentNotFoundException;
import org.java4web.exceptions.DoctorNotFoundException;
import org.java4web.model.*;
import org.java4web.repositories.AppointmentRepository;
import org.java4web.repositories.DoctorRepository;
import org.java4web.repositories.PatientRepository;
import org.java4web.repositories.SpecialtyRepository;
import org.java4web.utils.AppointmentDto;

import org.java4web.utils.Utils;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.security.Principal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AppointmentService {


    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final SpecialtyRepository specialtyRepository;
    Date today = Utils.date;

    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository,
                              DoctorRepository doctorRepository, SpecialtyRepository specialtyRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.specialtyRepository = specialtyRepository;

    }

    public Appointment newAppointment(@Valid AppointmentDto appointmentDto, Principal principal) {
        Date appointmentDate = Utils.dateFormatParse(appointmentDto.getDate());

        if (!appointmentDate.before(today)) {

            Appointment entityAppointment = new Appointment();

            Patient patient = patientRepository.findByUsername(principal.getName());

            entityAppointment.setPatient(patient);
            Optional<Doctor> doctorOptional = doctorRepository.findById(appointmentDto.getDoctorId());
            if (doctorOptional == null) {
                throw new DoctorNotFoundException(appointmentDto.getDoctorId().toString());
            }

            Doctor doctor = doctorOptional.get();
            entityAppointment.setDoctor(doctor);
            entityAppointment.setDate(Utils.dateFormatParse(appointmentDto.getDate()));
            entityAppointment.setDescr(appointmentDto.getDescr());
            entityAppointment.setNotes(appointmentDto.getNotes());

            return appointmentRepository.save(entityAppointment);


        }

        return null;
    }

    public List<Appointment> getAppointments(Principal principal, String specialtyIdStr, String dateFrom, String dateTo) {

        if (specialtyIdStr != null) {
            Long specialtyId = Long.valueOf(specialtyIdStr);
            Optional<Specialty> specialty = specialtyRepository.findById(specialtyId);
            if (dateFrom == null && dateTo == null) {
                //TODO: Catch Long parsing.
                if (specialty.isPresent()) {
                    return appointmentRepository.findBySpecialtyId(specialty.get().getId());
                }
            } else if (dateFrom != null && dateTo != null) {
                Date fromDate = Utils.dateFormatParse(dateFrom);
                Date toDate = Utils.dateFormatParse(dateTo);
                return appointmentRepository.findBySpecialtyIdAndDateTimeBetween(specialtyId, fromDate, toDate);
            } else {
                //TODO:
            }
        } else if (dateFrom != null && dateTo != null) {
            Date fromDate = Utils.dateFormatParse(dateFrom);
            Date toDate = Utils.dateFormatParse(dateTo);
            return appointmentRepository.findByDateTimeBetween(fromDate, toDate);
        }

        return null;


    }

    @PutMapping("/appointments/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody @Valid AppointmentDto appointmentDtoToUpdate, Principal principal) {

        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);


        Date newDate = Utils.dateFormatParse(appointmentDtoToUpdate.getDate());
        if (!newDate.before(today)) {

            Appointment appointmentToUpdate = optionalAppointment.get();


            String changedDescription = appointmentDtoToUpdate.getDescr();
            String changedNotes = appointmentDtoToUpdate.getNotes();
            appointmentToUpdate.setDescr(changedDescription);
            appointmentToUpdate.setNotes(changedNotes);

            appointmentToUpdate.setDate(newDate);

            return appointmentRepository.save(appointmentToUpdate);


        } else {
            throw new AppointmentCannotBeUpgrated();
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

