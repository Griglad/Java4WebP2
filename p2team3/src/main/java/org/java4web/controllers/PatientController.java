package org.java4web.controllers;

import org.java4web.exceptions.CreateRecordException;
import org.java4web.exceptions.ExceptionMessages;
import org.java4web.exceptions.PatientException;
import org.java4web.model.Patient;
import org.java4web.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class PatientController {


    private PatientRepository patientRepository;


    @Autowired
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;

    }

    @PostMapping(value = "/save")
    public
    Patient newPatient(@RequestBody @Valid Patient patient) {

        if (patientRepository.findByEmail(patient.getEmail()) != null) {
            throw new CreateRecordException(ExceptionMessages.EMAIL_ALREADY_EXISTS.getErrorMessage());


        }
        if (patientRepository.findByAmka(patient.getAmka()) != null) {
            throw new CreateRecordException(ExceptionMessages.AMKA_ALREADY_EXISTS.getErrorMessage());

        }

        if (patientRepository.findPatientByUsername(patient.getUsername()) != null) {

            throw new CreateRecordException(ExceptionMessages.USERNAME_ALREADY_EXISTS.getErrorMessage());
        }

        patientRepository.save(patient);


        return null;
    }


    @GetMapping("/patients/{id}")
    public Patient getPatient(@PathVariable Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientException(id));
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
