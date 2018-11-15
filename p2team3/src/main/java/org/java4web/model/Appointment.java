package org.java4web.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id",nullable = false)
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id",nullable = false)
    private Doctor doctor;

    @NotNull
    @Column(nullable = false)
    private Date dateTime;

    @NotNull
    @Column(nullable = false)
    private String descr;

    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    private String notes;


    public Appointment() {
    }

    public Appointment(Date dateTime, String descr, String notes) {
        this.dateTime = dateTime;
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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
        return String.format(
                "Appointment[id=%d, patient_id='%d', doctor_id='%d', date=%d, description='%s', notes='%s']",
                id, patient.getId(), doctor.getId(), dateTime, descr, notes);
    }
}

