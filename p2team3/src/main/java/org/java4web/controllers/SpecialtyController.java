package org.java4web.controllers;

import org.java4web.model.Specialty;
import org.java4web.services.SpecialtyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpecialtyController {
    private final SpecialtyService specialtyService;

    @Autowired
    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @GetMapping("/specialties")
    public List<Specialty> getAllSpecialties() { return specialtyService.getAllSpecialties(); }

    @GetMapping("/specialties/{id}")
    public Specialty getSpecialty(@PathVariable Long id) { return specialtyService.getSpecialty(id); }
}
