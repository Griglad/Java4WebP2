package org.java4web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.java4web.utils.Utils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", nullable = false)
    protected Patient patient;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", nullable = false)
    protected Doctor doctor;

    @Column(nullable = false, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateTime;

    @Column
    protected String descr;

    @Column(columnDefinition = "TEXT")
    protected String notes;


    public Appointment() {
    }

    public Appointment(Doctor doctor, Date dateTime, String descr, String notes) {
        this.doctor = doctor;
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

    public Date getDate() {
        return dateTime;
    }

    public void setDate(Date date) {
        Utils.dateFormat.format(date);
        this.dateTime = date;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
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
                //TODO: Import date string
                //", date='" + date + '\'' +
                ", descr='" + descr + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}

