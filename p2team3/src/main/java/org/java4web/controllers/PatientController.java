package org.java4web.controllers;

import org.java4web.exceptions.CreateRecordException;
import org.java4web.exceptions.ExceptionMessages;
import org.java4web.exceptions.PatientException;
import org.java4web.model.Patient;
import org.java4web.repositories.PatientRepository;
import org.java4web.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class PatientController {


    private PatientService patientService;


    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;

    }

    @PostMapping(value = "/save")
    public Patient newPatient(@RequestBody @Valid Patient patient) {

        Patient pat = patientService.newPatient(patient);
        return pat;
    }


    @GetMapping("/patients/{id}")
    public Patient getPatient(@PathVariable Long id) {
        return patientService.getPat(id);
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler({MethodArgumentNotValidException.class})
//    public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        return ex.getBindingResult()
//                .getAllErrors().stream()
//                .map(ObjectError::getDefaultMessage)
//                .collect(Collectors.toList());
//
//
//    }


}
