package org.java4web.services;

import org.java4web.exceptions.AppointmentNotFoundException;
import org.java4web.exceptions.DoctorNotFoundException;
import org.java4web.exceptions.PatientNotFoundException;
import org.java4web.exceptions.SpecialtyNotFoundException;
import org.java4web.model.Appointment;
import org.java4web.model.Doctor;
import org.java4web.model.Patient;
import org.java4web.model.Specialty;
import org.java4web.repositories.AppointmentRepository;
import org.java4web.repositories.DoctorRepository;
import org.java4web.repositories.PatientRepository;
import org.java4web.repositories.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;
    private SpecialtyRepository specialtyRepository;
    private AppointmentRepository appointmentRepository;


    @Autowired
    public DoctorService(DoctorRepository doctorRepository, SpecialtyRepository specialtyRepository,AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.specialtyRepository = specialtyRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }


    public Doctor getDoc(String username) {
        if (doctorRepository.findByUsername(username) == null) {
            throw new DoctorNotFoundException(username);
        } else {
            return doctorRepository.findByUsername(username);
        }
    }

    public List<Doctor> getDoctorsBySpecialty(String specialtyName) {
        Specialty specialty = specialtyRepository.findByName(specialtyName);
        if (specialty == null) {
            throw new SpecialtyNotFoundException(specialtyName);
        }

        List<Doctor> doctors = doctorRepository.findBySpecialty(specialty);
        if (doctors.isEmpty()) {
            throw new SpecialtyNotFoundException(specialtyName);
        } else {
            return doctors;
        }
    }

    public Appointment getDoctorAppointment(@PathVariable Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(id));
    }
}



