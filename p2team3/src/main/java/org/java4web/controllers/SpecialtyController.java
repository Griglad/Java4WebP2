package org.java4web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        return specialtyRepository.findById(id);
                //.orElseThrow(() -> new SpecialtyNotFoundException(id));
    }
}
