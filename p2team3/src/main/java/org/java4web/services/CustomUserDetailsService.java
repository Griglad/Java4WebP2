package org.java4web.services;

import org.java4web.model.Doctor;
import org.java4web.repositories.DoctorRepository;
import org.java4web.repositories.PatientRepository;
import org.java4web.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public CustomUserDetailsService(DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Doctor doctor = doctorRepository.findByUsername(username);
        if(doctor == null){
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(doctor);
    }

    public User findUserByUsername(String username){
        if(username){

        } else {

        }
    }
}