package org.java4web.repositories;

import org.java4web.model.Doctor;
import org.java4web.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    Specialty findByName(String name);

}

