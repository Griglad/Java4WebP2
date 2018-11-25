package org.java4web.model;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Doctor implements CustomUser{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "First name must not be blank")
    @NotNull(message = "First name must not be null")
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name should contain only letters")
    private String firstName;


    @NotBlank(message = "Last name must not be blank")
    @NotNull(message = "Last name must not be Null")
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should contain only letters")
    private String lastName;



    @NotBlank(message = "Specialty must not be blank")
    @NotNull(message = "Specialty must not be null")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;


    @NotBlank(message = "Username cannot be blank")
    @NotNull(message = "Username cannot be null")
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Please provide a password with letters from A-Z and digits from 0-9")
    @Size(min = 6, max = 30, message = "Please provide a username from 6-30 characters")
    private String username;


    @NotBlank(message = "Password cannot be blank")
    @NotNull(message = "Password cannot be null")
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Please provide a password with letters from A-Z and digits from 0-9")

    @Size(min = 6, max = 60, message = "Please provide a password from 6-30 characters")
    private String password;

    public Doctor() {
    }

    public Doctor(String firstname, String lastname, Specialty specialty, String username, String password) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.specialty = specialty;
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

