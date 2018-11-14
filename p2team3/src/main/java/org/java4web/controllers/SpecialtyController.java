package org.java4web.controllers;

import org.java4web.model.Specialty;
import org.java4web.repositories.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class SpecialtyController {
    private final SpecialtyRepository specialtyRepository;

    @Autowired
    public SpecialtyController(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @GetMapping("/specialties")
    public List<Specialty> getSpecialties() { return specialtyRepository.findAll(); }

    @GetMapping("/specialties/{id}")
    public Specialty getBook(@PathVariable Long id) {
        return new Specialty();
        //return specialtyRepository.findById(id);
                //.orElseThrow(() -> new SpecialtyNotFoundException(id));
    }
}
