package org.java4web.controllers;

import org.java4web.exceptions.DoctorNotFoundException;
import org.java4web.model.Doctor;
import org.java4web.model.Patient;
import org.java4web.model.Specialty;
import org.java4web.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DoctorController {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/doctors")
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    @GetMapping("/doctors/{username}")
    public Doctor getDoctor(@PathVariable String username){

        return doctorRepository.findByUsername(username);
             //   .orElseThrow(() -> new DoctorNotFoundException(username));
    }

    @PostMapping(value = "/saved")
    public @ResponseBody
    Doctor newDoctor(@RequestBody  @Valid Doctor doctor) {

        doctorRepository.save(doctor);

     return null;
    }

}
