package org.java4web.repositories;

import org.java4web.model.Doctor;
import org.java4web.model.Patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUsername (String username);
}
