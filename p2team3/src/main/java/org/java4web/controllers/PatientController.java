package org.java4web.controllers;

import org.java4web.model.Patient;
import org.java4web.services.PatientService;
import org.java4web.utils.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Validated
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients/{id}")
    public Patient getPatient(@PathVariable Long id) {
        return patientService.getPatient(id);
    }

    @PostMapping("/patients")
    public Patient newPatient(@RequestBody @Valid PatientDto patientDto){
        return patientService.newPatient(patientDto);
    }
}
