package org.java4web.security;

public enum Role {
    ADMIN ('a'),
    PATIENT ('p'),
    DOCTOR ('d');

    private final char prefix;

    private Role(char c) {
        prefix = c;
    }

    public char getPrefix(){
        return prefix;
    }

    public boolean equalsPrefix(char otherPrefix) {
        return prefix == otherPrefix;
    }
}
