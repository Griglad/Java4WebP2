package org.java4web.utilities;

import org.java4web.model.Patient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PatientDto extends Patient {

    @NotBlank(message = "{first.not.blank}")
    @NotNull(message = "{first.not.null}")
    @Pattern(regexp = "^[A-Za-zΑ-Ωα-ωίϊΐόάέύϋΰήώ]+$", message = "{first.only.letters}")
    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @NotBlank(message = "{last.not.blank}")
    @NotNull(message = "{last.not.null}")
    @Pattern(regexp = "^[A-Za-zΑ-Ωα-ωίϊΐόάέύϋΰήώ]+$", message = "{last.only.letters}")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @NotBlank(message = "{email.not.blank}")
    @NotNull(message = "{email.not.null")
    @Pattern(regexp = ".+@.+\\..+", message = "{email.valid}")
    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    @NotBlank(message = "{mobile.not.blank}")
    @NotNull(message = "{mobile.not.null")
    @Pattern(regexp = "^(\\d{10}|\\d{14})$", message = "{mobile.digits}")
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @NotBlank(message = "{user.not.blank}")
    @NotNull(message = "{user.not.null}")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{user.letters.digits}")
    @Size(min = 6, max = 30, message = "{user.size}")
    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank(message = "{amka.not.blank}")
    @NotNull(message = "{amka.not.null}")
    @Pattern(regexp = "^[0-9]{11}$", message = "{amka.only.digits}")
    public String getAmka() {
        return super.getAmka();
    }

    @NotBlank(message = "{pass.not.blank}")
    @NotNull(message = "{pass.not.null}")
    @Size(max = 60, message = "{pass.size}")
    public String getPassword() {
        return super.getPassword();
    }
}
