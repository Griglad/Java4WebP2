package org.java4web.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.java4web.utils.Utils;
import javax.persistence.*;
import java.util.Date;

@JsonFilter("AppointmentFilter")
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    protected Patient patient;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", nullable = false)
    protected Doctor doctor;

    @Column(nullable = false, columnDefinition="DATETIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Utils.dateTimeFormat, timezone="Europe/Athens")
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        Utils.dateFormat.format(dateTime);
        this.dateTime = dateTime;
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
                ", date='" + Utils.dateFormat.format(dateTime) + '\'' +
                ", descr='" + descr + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}

