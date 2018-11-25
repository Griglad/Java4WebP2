package org.java4web.services;

import org.java4web.exceptions.RoleNotFoundException;
import org.java4web.model.CustomUser;
import org.java4web.repositories.DoctorRepository;
import org.java4web.repositories.PatientRepository;
import org.java4web.security.CustomUserDetails;
import org.java4web.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public CustomUserDetailsService(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameWithPrefix) throws UsernameNotFoundException {

        char role = usernameWithPrefix.charAt(0);
        String username = usernameWithPrefix.substring(1);
        CustomUser user = findUserByUsername(username, role);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    public CustomUser findUserByUsername(String username, char role) throws RoleNotFoundException {

        if(role == Role.DOCTOR.getPrefix()){
            return doctorRepository.findByUsername(username);
        } else if(role == Role.PATIENT.getPrefix()) {
            return patientRepository.findByUsername(username);
        }
        throw new RoleNotFoundException(role);
    }

}