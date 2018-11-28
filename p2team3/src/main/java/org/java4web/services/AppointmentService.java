package org.java4web.services;

import org.java4web.exceptions.DoctorNotFoundException;
import org.java4web.model.Appointment;
import org.java4web.model.CustomUser;
import org.java4web.model.Doctor;
import org.java4web.model.Patient;
import org.java4web.repositories.AppointmentRepository;
import org.java4web.repositories.DoctorRepository;
import org.java4web.repositories.PatientRepository;
import org.java4web.security.UserRole;
import org.java4web.utils.AppointmentDto;
import org.java4web.utils.PatientDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Service
public class AppointmentService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;


    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository,
                              DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;


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
        entityAppointment.setDate(appointmentDto.getDate());
        entityAppointment.setTime(appointmentDto.getTime());
        entityAppointment.setDescr(appointmentDto.getDescr());
        entityAppointment.setNotes(appointmentDto.getNotes());

        return appointmentRepository.save(entityAppointment);

    }


//        modelMapper.addMappings(new PropertyMap<AppointmentDto, Appointment>() {
//            protected void configure() {
//
//
//            }
//        });


}



