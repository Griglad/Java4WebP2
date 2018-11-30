package org.java4web.controllers;

import org.java4web.model.Appointment;
import org.java4web.model.Doctor;

import org.java4web.model.Patient;
import org.java4web.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctors")
    public List<Doctor> getDoctors() {
        return doctorService.getAll();

    }


    @GetMapping("/doctors/{username}")
    public Doctor getDoctor(@PathVariable String username) {
        return doctorService.getDoc(username);

    }

    @GetMapping("/doctors/spec/{specialty}")
    public List<Doctor> getDoctors(@PathVariable String specialty) {
        return doctorService.getDoctorsBySpecialty(specialty);
    }

    @GetMapping("doctors/{id}")
    public Appointment getDoctorAppointmet(@PathVariable Long id) {
        return doctorService.getDoctorAppointment(id);

    }

    @GetMapping("/doctors/patient/{id}")
    public Patient getPatient(@PathVariable Long id) {
        return doctorService.getPatient(id);
    }
}
