package org.java4web.model;


import org.java4web.WebAppConfig;
import org.java4web.security.UserRole;
import org.java4web.utils.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.validation.constraints.*;

import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
public class Patient implements CustomUser{



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


    @Column(nullable = false)
    protected String firstName;


    @Column(nullable = false)
    protected String lastName;


    @Column(nullable = false, unique = true)
    protected String amka;


    @Column(nullable = false, unique = true)
    protected String email;


    @Column(nullable = false)
    protected String mobilePhone;


    @Column(nullable = false, unique = true)
    protected String username;


    @Column(nullable = false)
    private String password;

    public Patient() {
    }

    public Patient(String firtstName, String lastName, String amka, String email, String mobilePhone, String username, String password) {
        this.firstName = firtstName;
        this.lastName = lastName;
        this.amka = amka;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "Patient[id=%d, first_name='%s', last_name='%s', amka=%d, email='%s', mobile_phone=%d]",
                id, firstName, lastName, amka, email, mobilePhone);
    }


}

