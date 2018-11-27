package org.java4web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @NotNull
    @Column(nullable = false)
    private String date;

    @NotNull
    @Column(nullable = false)
    private String time;

    @NotNull
    @Column(nullable = false)
    private String descr;

    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    private String notes;


    public Appointment() {
    }



    public Appointment(Doctor doctor, @NotNull String date, @NotNull String time, @NotNull String descr, @NotNull String notes) {
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.descr = descr;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String desc) {
        this.descr = desc;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", descr='" + descr + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}

