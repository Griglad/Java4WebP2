package org.java4web.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Patient implements CustomUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Size(max = 30)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    @Size(max = 30)
    private String lastName;

    @NotNull
    @Column(nullable = false, unique = true)
    private Long amka;

    @NotNull
    @Column(nullable = false,unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private Long mobilePhone;


    @NotNull
    @Column(nullable = false, unique = true)
    @Size(max = 50)
    private String username;

    @NotNull
    @Column(nullable = false)
    @Size(max = 30)
    private String password;

    public Patient() {
    }

    public Patient(String firtstName, String lastName, Long amka, String email, Long mobilePhone, String username, String password) {
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

    public Long getAmka() {
        return amka;
    }

    public void setAmka(Long amka) {
        this.amka = amka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(Long mobilePhone) {
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

