package org.java4web;

import org.java4web.model.Doctor;
import org.java4web.model.Patient;
import org.java4web.repositories.DoctorRepository;
import org.java4web.repositories.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LoadDatabase {


    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PatientRepository patientRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
//            logger.info("Preloading " + bookRepository.save(new Book("The Grapes of Wrath", "0143125508")));
//            logger.info("Preloading " + bookRepository.save(new Book("Symposium", "0872200760")));
//            logger.info("Preloading " + bookRepository.save(new Book("Pride and Prejudice", "0486284735")));
//            logger.info("Preloading " + bookRepository.save(new Book("The Great Gatsby", "9780141182636")));

//            Patient patient = new Patient("Thomas", "Kuriakou",
//                    "4587458965", "tomkur@gmail.com", "6977412563",
//                    "tKuriakou",passwordEncoder.encode("54542121"));

            //logger.info("Preloading " + patientRepository.save(patient));
            //patientRepository.save(patient);

        };
    }
}
