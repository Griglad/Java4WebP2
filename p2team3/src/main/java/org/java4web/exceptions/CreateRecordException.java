package org.java4web.exceptions;


public class CreateRecordException extends RuntimeException {
private String errorMessage;


    public CreateRecordException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
