package org.java4web.security;

public enum UserRole {
    ADMIN ('a'),
    PATIENT ('p'),
    DOCTOR ('d');

    private final char prefix;

    private UserRole(char c) {
        prefix = c;
    }

    public char getPrefix(){
        return prefix;
    }

    public boolean equalsPrefix(char otherPrefix) {
        return prefix == otherPrefix;
    }
}