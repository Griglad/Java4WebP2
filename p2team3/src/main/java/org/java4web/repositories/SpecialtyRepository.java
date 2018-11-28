package org.java4web.repositories;


import org.java4web.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    public Specialty findByName (String name);
}

