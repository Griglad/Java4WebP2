package org.java4web.utils;

import org.java4web.model.Appointment;
import javax.validation.constraints.NotNull;


public class AppointmentDto extends Appointment {


    private Long doctorId;



    @NotNull
    public String getDate() {
        return super.getDate();
    }

    @NotNull
    public String getTime() {
        return super.getTime();
    }

    @NotNull
    public String getDescr() {
        return super.getDescr();
    }

    @NotNull
    public String getNotes() {
        return super.getNotes();
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}







