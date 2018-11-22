package org.java4web.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse{

private int errorCode;
private String message;
private ArrayList<String> list;

public int getErrorCode()
{
    return errorCode;
}

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void setMessage(ArrayList<String> messages)
    {
        this.list = messages;
    }
}
