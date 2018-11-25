package org.java4web.exceptions;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(char c) {
        super("Could not find role with prefix " + c);
    }
}
