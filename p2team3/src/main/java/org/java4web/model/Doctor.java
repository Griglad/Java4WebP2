package org.java4web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

import javax.persistence.*;

@Entity
public class Doctor implements CustomUser{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    protected String firstName;

    @Column(nullable = false)
    protected String lastName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "specialty_id")
    protected Specialty specialty;

    @Column(nullable = false, unique = true)
    protected String username;

    @Column(nullable = false)
    protected String password;

    public Doctor() {
    }

    public Doctor(String firstname, String lastname, Specialty specialty, String username, String password) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.specialty = specialty;
        this.username = username;
        this.password = password;
    }

    public void setFiltersForGetAppointments(MappingJacksonValue wrapper){
        wrapper.setFilters(new SimpleFilterProvider().addFilter("AppointmentFilter", SimpleBeanPropertyFilter.serializeAllExcept("doctor")));
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
    public void setPassword(String password) {
        this.password = password;
    }


    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return String.format(
                "Doctor[id=%d, first_name='%s',last_name='%s', specialty='%s']",
                id, firstName, lastName, specialty.getName());
    }
}

