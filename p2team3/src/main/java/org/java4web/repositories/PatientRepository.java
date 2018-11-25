package org.java4web.repositories;

import org.java4web.model.Doctor;
import org.java4web.model.Patient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {


    public Patient findByEmail(String email);

    public Patient findByAmka(String amka);

    public Patient findByUsername(String username);

}
