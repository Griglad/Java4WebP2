package org.java4web.exceptions;

import org.springframework.security.core.parameters.P;

public class AppointmentCannotBeUpgrated extends RuntimeException{
    public AppointmentCannotBeUpgrated()
    {
        super("Appointment cannot be Upgrated");
    }

}
