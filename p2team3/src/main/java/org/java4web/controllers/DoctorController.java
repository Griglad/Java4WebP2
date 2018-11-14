package org.java4web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DoctorController {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/doctors")
    public List<Doctor> getDoctors() { return doctorRepository.findAll(); }

    @GetMapping("/doctors/{username}")
    public Doctor getDoctor(@PathVariable String username){
        return new Doctor();
        //return doctorRepository.findById(id);
                //.orElseThrow(() -> new DoctorNotFoundException(id));
    }
}
