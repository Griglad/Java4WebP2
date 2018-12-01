package org.java4web.exceptions;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException(String username) {
        super("Could not find doctor with username " + username);
    }

    public DoctorNotFoundException(Long id) {
        super("Could not find doctor with id " + id.toString());
    }
}
