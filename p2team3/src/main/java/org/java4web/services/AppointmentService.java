package org.java4web.services;

import org.java4web.exceptions.DoctorNotFoundException;
import org.java4web.model.*;
import org.java4web.repositories.AppointmentRepository;
import org.java4web.repositories.DoctorRepository;
import org.java4web.repositories.PatientRepository;
import org.java4web.repositories.SpecialtyRepository;
import org.java4web.utils.AppointmentDto;

import org.java4web.utils.Utils;

import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.security.Principal;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public Appointment newAppointment(@Valid AppointmentDto appointmentDto, Principal principal) {
        Appointment entityAppointment = new Appointment();


        Patient patient = patientRepository.findByUsername(principal.getName());
        entityAppointment.setPatient(patient);
        // Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId()).get();
//        ModelMapper modelMapper = new ModelMapper();
//         modelMapper.addMappings(
//                 new PropertyMap<Appointment, AppointmentDto>() {
//
//                     protected void configure()
//                     {
//                         map().setDoctor(doctor);
//
//                     }
//                 }
//         );
        Optional<Doctor> doctorOptional = doctorRepository.findById(appointmentDto.getDoctorId());

        if (doctorOptional == null) {
            throw new DoctorNotFoundException(appointmentDto.getDoctorId().toString());
        }
        //modelMapper.map(appointmentDto, entityAppointment);
        Doctor doctor = doctorOptional.get();
        entityAppointment.setDoctor(doctor);
        //entityAppointment.setPatient(patient);


        entityAppointment.setDate(Utils.dateFormatParse(appointmentDto.getDate()));
        entityAppointment.setDescr(appointmentDto.getDescr());
        entityAppointment.setNotes(appointmentDto.getNotes());

        return appointmentRepository.save(entityAppointment);
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
                return appointmentRepository.findBySpecialtyIdAndDateTimeBetween(specialtyId,fromDate, toDate);
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

//        modelMapper.addMappings(new PropertyMap<AppointmentDto, Appointment>() {
//            protected void configure() {
//
//
//            }
//        });

}



