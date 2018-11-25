package org.java4web.exceptions;

public class PatientException extends RuntimeException {
    public PatientException(long id) {
        super("Could not find patient with username " + id);
    }
}
