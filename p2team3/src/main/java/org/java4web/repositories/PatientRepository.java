package org.java4web.repositories;

import org.java4web.model.Patient;
import org.springframework.data.repository.CrudRepository;


public interface PatientRepository extends CrudRepository<Patient, Long> {


   public Patient findByEmail(String email);

    public Patient findByAmka(String amka);


    Patient findByUsername(String username);
}
