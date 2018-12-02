package org.java4web.exceptions;

public enum ExceptionMessagesForExistingValues {

    EMAIL_ALREADY_EXISTS("This Email already exists"),
    AMKA_ALREADY_EXISTS("This Amka already exists"),
    USERNAME_ALREADY_EXISTS("This Username already exists");


    private String ErrorMessage;


    ExceptionMessagesForExistingValues(String errorMessage) {
        this.ErrorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }


}
