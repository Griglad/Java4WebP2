package org.java4web.services;

import org.java4web.exceptions.CreateRecordException;
import org.java4web.exceptions.ExceptionMessages;
import org.java4web.exceptions.PatientException;
import org.java4web.model.Patient;
import org.java4web.repositories.PatientRepository;
import org.java4web.utils.PatientDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
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
            throw new CreateRecordException(ExceptionMessages.EMAIL_ALREADY_EXISTS.getErrorMessage());
        }
        if (patientRepository.findByAmka(patientDto.getAmka()) != null) {
            throw new CreateRecordException(ExceptionMessages.AMKA_ALREADY_EXISTS.getErrorMessage());

        }

        if (patientRepository.findByUsername(patientDto.getUsername()) != null) {
            throw new CreateRecordException(ExceptionMessages.USERNAME_ALREADY_EXISTS.getErrorMessage());
        }

        Patient entityPatient = new Patient();
        patientDto.setPassword(passwordEncoder.encode(patientDto.getPassword()));
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.map(patientDto, entityPatient);


        return patientRepository.save(entityPatient);


    }

    public Patient getPat(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientException(id));
    }


}
