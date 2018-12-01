package org.java4web.exceptions;

public class SpecialtyNotFoundException extends RuntimeException {
    public SpecialtyNotFoundException(String specialty) {
        super("Could not find specialty with username " + specialty);
    }

    public SpecialtyNotFoundException(Long id) {
        super("Could not find specialty with id " + id);
    }
}
