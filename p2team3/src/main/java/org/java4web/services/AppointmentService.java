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

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final SpecialtyRepository specialtyRepository;


    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository,
                              DoctorRepository doctorRepository,SpecialtyRepository specialtyRepository) {
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

    public List<Appointment> getAppointments(Principal principal, String specialtyName)
    {
                 Specialty specialtyForAppointment = specialtyRepository.findByName(specialtyName);



               return appointmentRepository.findBySpecialty(specialtyForAppointment.getName());
    }

//        modelMapper.addMappings(new PropertyMap<AppointmentDto, Appointment>() {
//            protected void configure() {
//
//
//            }
//        });

}



