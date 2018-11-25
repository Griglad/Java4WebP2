package org.java4web.services;

import org.java4web.exceptions.CreateRecordException;
import org.java4web.exceptions.ExceptionMessages;
import org.java4web.exceptions.PatientException;
import org.java4web.model.Patient;
import org.java4web.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;

    }

    public Patient newPatient(Patient patient) {

        if (patientRepository.findByEmail(patient.getEmail()) != null) {
            throw new CreateRecordException(ExceptionMessages.EMAIL_ALREADY_EXISTS.getErrorMessage());

        }
        if (patientRepository.findByAmka(patient.getAmka()) != null) {
            throw new CreateRecordException(ExceptionMessages.AMKA_ALREADY_EXISTS.getErrorMessage());

        }

        if (patientRepository.findByUsername(patient.getUsername()) != null) {

            throw new CreateRecordException(ExceptionMessages.USERNAME_ALREADY_EXISTS.getErrorMessage());
        }

       return patientRepository.save(patient);



    }

    public Patient getPat(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientException(id));
    }

}
