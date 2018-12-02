package org.java4web.services;

import org.java4web.exceptions.CreateRecordException;
import org.java4web.exceptions.ExceptionMessagesForExistingValues;
import org.java4web.exceptions.PatientNotFoundException;
import org.java4web.model.Patient;
import org.java4web.repositories.PatientRepository;
import org.java4web.utilities.PatientDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient newPatient(@Valid PatientDto patientDto) {

        if (patientRepository.findByEmail(patientDto.getEmail()) != null) {
            throw new CreateRecordException(ExceptionMessagesForExistingValues.EMAIL_ALREADY_EXISTS.getErrorMessage());
        }
        if (patientRepository.findByAmka(patientDto.getAmka()) != null) {
            throw new CreateRecordException(ExceptionMessagesForExistingValues.AMKA_ALREADY_EXISTS.getErrorMessage());

        }

        if (patientRepository.findByUsername(patientDto.getUsername()) != null) {
            throw new CreateRecordException(ExceptionMessagesForExistingValues.USERNAME_ALREADY_EXISTS.getErrorMessage());
        }

        Patient entityPatient = new Patient();
        patientDto.setPassword(passwordEncoder.encode(patientDto.getPassword()));
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.map(patientDto, entityPatient);

        return patientRepository.save(entityPatient);
    }

    public Patient getPatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
    }

    public Patient getPatientByUsername(String username){
        return patientRepository.findByUsername(username);
    }
}
