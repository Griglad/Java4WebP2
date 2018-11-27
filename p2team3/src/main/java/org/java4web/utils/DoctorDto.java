package org.java4web.utils;

import org.java4web.model.Doctor;
import org.java4web.model.Specialty;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class DoctorDto extends Doctor {


    public Long getId() {
        return super.getId();
    }

    @NotBlank(message = "First name must not be blank")
    @NotNull(message = "First name must not be null")
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name should contain only letters")
    public String getFirstName() {
        return super.getFirstName();
    }

    @NotBlank(message = "Last name must not be blank")
    @NotNull(message = "Last name must not be Null")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should contain only letters")
    public String getLastName() {
        return super.getLastName();
    }

    @NotBlank(message = "Username cannot be blank")
    @NotNull(message = "Username cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Please provide a password with letters from A-Z and digits from 0-9")
    @Size(min = 6, max = 30, message = "Please provide a username from 6-30 characters")
    public String getUsername() {
        return super.getUsername();
    }

    @NotBlank(message = "Password cannot be blank")
    @NotNull(message = "Password cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Please provide a password with letters from A-Z and digits from 0-9")
    @Size(min = 6, max = 60, message = "Please provide a password from 6-30 characters")
    public String getPassword() {
        return super.getPassword();
    }

    @NotBlank(message = "Specialty must not be blank")
    @NotNull(message = "Specialty must not be null")
    public Specialty getSpecialty() {
        return super.getSpecialty();
    }
}
