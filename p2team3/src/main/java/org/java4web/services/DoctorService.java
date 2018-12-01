package org.java4web.services;

import org.java4web.exceptions.DoctorNotFoundException;
import org.java4web.model.Doctor;
import org.java4web.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;
    private SpecialtyService specialtyService;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, SpecialtyService specialtyService) {
        this.doctorRepository = doctorRepository;
        this.specialtyService = specialtyService;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctor(Long id){
        return doctorRepository.findById(id)
            .orElseThrow(() -> new DoctorNotFoundException(id));
    }

    public Doctor getDoctor(String username) {
        Doctor doctor = doctorRepository.findByUsername(username);
        if (doctor== null) {
            throw new DoctorNotFoundException(username);
        } else {
            return doctor;
        }
    }

    public List<Doctor> getDoctorsBySpecialty(Long id) {
        return doctorRepository.findBySpecialty(specialtyService.getSpecialty(id));
    }
}



