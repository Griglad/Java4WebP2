package org.java4web.model;


import javax.persistence.*;

@Entity
public class Patient {
    private Long id;
    private String firstName;
    private String lastName;
    private Long amka;
    private String email;
    private Long mobilePhone;
    private String username;
    private String password;

    public Patient() {
    }

    public Patient(String firtstName, String lastName,Long amka, String email, Long mobilePhone, String username,String password) {
        this.firstName = firtstName;
        this.lastName = lastName;
        this.amka = amka;
        this.email = email;
        this.mobilePhone = mobilePhone;
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

