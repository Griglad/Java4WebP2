package org.java4web.controllers;

import org.java4web.model.Doctor;
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
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/doctors/{id}")
    public Doctor getDoctor(@PathVariable Long id) {
        return doctorService.getDoctor(id);
    }

    @GetMapping("/doctors/spec/{specialtyId}")
    public List<Doctor> getDoctors(@PathVariable Long specialtyId) {
        return doctorService.getDoctorsBySpecialty(specialtyId);
    }
}
