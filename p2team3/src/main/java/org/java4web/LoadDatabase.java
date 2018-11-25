package org.java4web;

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

//            Patient patient = new Patient("Thomas", "Kuriakou",
//                    "4587458965", "tomkur@gmail.com", "6977412563",
//                    "tKuriakou",passwordEncoder.encode("54542121"));

            //logger.info("Preloading " + patientRepository.save(patient));
            //patientRepository.save(patient);

        };
    }
}
