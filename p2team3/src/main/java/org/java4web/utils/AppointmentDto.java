package org.java4web.utils;

import org.java4web.model.Appointment;
import javax.validation.constraints.NotNull;


public class AppointmentDto {

    private Long doctorId;
    private String date;
    private String descr;
    private String notes;

    @NotNull(message = "{date.not.null}")
    public String getDate() {
        return date;
    }


    public String getDescr() {
        return descr;
    }


    public String getNotes() {
        return notes;
    }

    public Long getDoctorId() {
        return doctorId;
    }
}







