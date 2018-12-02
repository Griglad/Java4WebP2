package org.java4web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

import javax.persistence.*;

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

    public void setFiltersForGetAppointments(MappingJacksonValue wrapper){
        wrapper.setFilters(new SimpleFilterProvider().addFilter("AppointmentFilter", SimpleBeanPropertyFilter.serializeAllExcept("patient")));
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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return String.format(
                "Patient[id=%d, first_name='%s', last_name='%s', amka='%s', email='%s', mobile_phone='%s']",
                id, firstName, lastName, amka, email, mobilePhone);
    }
}

