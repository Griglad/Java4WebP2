package org.java4web.exceptions;

public class DoctorNotFoundException extends RuntimeException {

    public DoctorNotFoundException(String username) {
        super("Could not find doctor with username " + username);
    }
}
