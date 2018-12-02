package org.java4web.exceptions;

public class AppointmentCannotBeUpdated extends RuntimeException{
    public AppointmentCannotBeUpdated(Long id)
    {
        super("Could not update Appointment with id " + id);
    }

}
