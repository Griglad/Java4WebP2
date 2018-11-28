package org.java4web.repositories;

import org.java4web.model.Appointment;
import org.java4web.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    @Query(value = "select * from Specialty inner join Doctor on  Specialty.id = Doctor.specialty_id inner join Appointment on Appointment.doctor_id = Doctor.id where Specialty.name = ?1", nativeQuery = true)
    public List<Appointment>findBySpecialty(String specialtyName);
}

