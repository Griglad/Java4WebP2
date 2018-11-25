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

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }


    public Doctor getDoc(String username) {


        if(doctorRepository.findByUsername(username) == null)
        {
            throw new DoctorNotFoundException(username);
        }
        else {
            return doctorRepository.findByUsername(username);
        }


    }
}



