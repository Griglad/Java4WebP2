package org.java4web.model;


import javax.persistence.*;


import javax.validation.constraints.*;

@Entity
public class Patient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{first.not.blank}")
    @NotNull(message = "{first.not.null}")
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "{first.only.letters}")
    private String firstName;


    @NotBlank(message = "{last.not.blank}")
    @NotNull(message = "{last.not.null}")
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "{last.only.letters}")
    private String lastName;

    @NotBlank(message = "{amka.not.blank}")
    @NotNull(message = "{amka.not.null}")
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[0-9]{9}$", message = "{amka.only.digits}")
    private String amka;


    @NotBlank(message = "{email.not.blank}")
    @NotNull(message = "{email.not.null")
    @Column(nullable = false, unique = true)
    @Pattern(regexp = ".+@.+\\..+", message = "{email.valid}")
    private String email;


    @NotBlank(message = "{mobile.not.blank}")
    @NotNull(message = "{mobile.not.null")
    @Column(nullable = false)
    @Pattern(regexp = "^(\\d{10}|\\d{12})$", message = "{mobile.digits}")
    private String mobilePhone;


    @NotBlank(message = "{user.not.blank}")
    @NotNull(message = "{user.not.null}")
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{user.letters.digits}")
    @Size(min = 6, max = 30, message = "{user.size}")
    private String username;

    @NotBlank(message = "{pass.not.blank}")
    @NotNull(message = "{pass.not.null}")
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{pass.letters.digits}")
    @Size(min = 6, max = 30, message = "{pass.size}")
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

