package org.java4web.repositories;

import org.java4web.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "select * from Appointment " +
            "inner join Doctor on Appointment.doctor_id = Doctor.id " +
            "inner join Specialty on Doctor.specialty_id = Specialty.id " +
            "where Specialty.id = ?1", nativeQuery = true)
    List<Appointment> findBySpecialtyId(Long specialtyId);

    List<Appointment>findByDateTimeBetween(Date dateFrom, Date dateTo);

    List<Appointment>findByDateTimeAfter(Date fromDate);

    List<Appointment>findByDateTimeBefore(Date toDate);

    @Query(value = "select * from Appointment " +
            "inner join Doctor on Appointment.doctor_id = Doctor.id " +
            "inner join Specialty on Doctor.specialty_id = Specialty.id " +
            "where (Specialty.id = ?1) and (Appointment.date_time between ?2 and ?3)", nativeQuery = true)
    List<Appointment> findBySpecialtyIdAndDateTimeBetween(Long specialty, Date fromDate, Date toDate);

    @Query(value = "select * from Appointment " +
            "inner join Doctor on Appointment.doctor_id = Doctor.id " +
            "inner join Specialty on Doctor.specialty_id = Specialty.id " +
            "where (Specialty.id = ?1) and (Appointment.date_time > ?2)", nativeQuery = true)
    List<Appointment> findBySpecialtyIdAndDateTimeAfter(Long specialty, Date dateFrom);

    @Query(value = "select * from Appointment " +
            "inner join Doctor on Appointment.doctor_id = Doctor.id " +
            "inner join Specialty on Doctor.specialty_id = Specialty.id " +
            "where (Specialty.id = ?1) and (Appointment.date_time < ?2)", nativeQuery = true)
    List<Appointment> findBySpecialtyIdAndDateTimeBefore(Long specialty, Date dateTo);
}

