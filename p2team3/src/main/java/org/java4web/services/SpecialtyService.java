package org.java4web.services;

import org.java4web.exceptions.SpecialtyNotFoundException;
import org.java4web.model.Specialty;
import org.java4web.repositories.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyService {

    SpecialtyRepository specialtyRepository;

    @Autowired
    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    public Specialty getSpecialty(Long id) {
        return specialtyRepository.findById(id)
                .orElseThrow(() -> new SpecialtyNotFoundException(id));
    }

    public List<Specialty> getAllSpecialties(){
        return specialtyRepository.findAll();
    }
}
