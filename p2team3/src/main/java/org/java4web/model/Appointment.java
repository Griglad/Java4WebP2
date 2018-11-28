package org.java4web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.java4web.utils.Utils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", nullable = false)
    protected Patient patient;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", nullable = false)
    protected Doctor doctor;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date date;

    @NotNull
    @Column(nullable = false)
    protected String descr;

    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    protected String notes;


    public Appointment() {
    }



    public Appointment(Doctor doctor, @NotNull Date date, @NotNull String descr, @NotNull String notes) {
        this.doctor = doctor;
        Utils.dateFormat.format(date);
        this.date = date;
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
        return date;
    }

    public void setDate(Date date) {
        Utils.dateFormat.format(date);
        this.date = date;
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
                //TODO: Import date string
                //", date='" + date + '\'' +
                ", descr='" + descr + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}

