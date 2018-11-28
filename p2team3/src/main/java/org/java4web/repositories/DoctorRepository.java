package org.java4web.repositories;

import org.java4web.model.Doctor;
import org.java4web.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {


    public  Doctor findByUsername (String username);

    public List<Doctor> findBySpecialty(Specialty specialty);
}

