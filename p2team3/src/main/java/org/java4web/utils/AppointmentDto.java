package org.java4web.utils;

import org.java4web.model.Appointment;
import javax.validation.constraints.NotNull;


public class AppointmentDto {

    private Long doctorId;
    private String date;
    private String descr;
    private String notes;

    @NotNull
    public String getDate() {
        return date;
    }

    @NotNull
    public String getDescr() {
        return descr;
    }

    @NotNull
    public String getNotes() {
        return notes;
    }

    public Long getDoctorId() {
        return doctorId;
    }
}







