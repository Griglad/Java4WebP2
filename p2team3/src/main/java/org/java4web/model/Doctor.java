package org.java4web.model;


import javax.persistence.*;

@Entity
public class Doctor {
    private Long id;
    private String firstName;
    private String lastName;
    private Specialty specialty;
    private String username;
    private String password;

    public Doctor() {
    }

    public Doctor(String firstname, String lastname, Specialty specialty,String username,String password) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.specialty = specialty;
        this.username = username;
        this.password = password;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "specialty_id")
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
                id, firstName, lastName,specialty.getName());
    }
}

