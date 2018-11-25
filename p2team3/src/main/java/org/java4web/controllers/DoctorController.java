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
    public List<Doctor> getDoctors() {
        return doctorService.getAll();
    }

    @GetMapping("/doctors/{username}")
    public Doctor getDoctor(@PathVariable String username){
        return doctorService.getDoc(username);

    }



}
