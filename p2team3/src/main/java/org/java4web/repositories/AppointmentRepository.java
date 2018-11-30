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
    public List<Appointment> findBySpecialtyId(Long specialtyId);

    public List<Appointment>findByDateTimeBetween(Date dateFrom, Date dateTo);

    public List<Appointment>findByDateTimeAfter(Date fromDate);

    public List<Appointment>findByDateTimeBefore(Date toDate);

    @Query(value = "select * from Appointment " +
            "inner join Doctor on Appointment.doctor_id = Doctor.id " +
            "inner join Specialty on Doctor.specialty_id = Specialty.id " +
            "where (Specialty.id = ?1) and (Appointment.date_time between ?2 and ?3)", nativeQuery = true)
    public List<Appointment> findBySpecialtyIdAndDateTimeBetween(Long specialty, Date fromDate, Date toDate);

    @Query(value = "select * from Appointment " +
            "inner join Doctor on Appointment.doctor_id = Doctor.id " +
            "inner join Specialty on Doctor.specialty_id = Specialty.id " +
            "where (Specialty.id = ?1) and (Appointment.date_time > ?2)", nativeQuery = true)
    public List<Appointment> findBySpecialtyIdAndDateTimeAfter(Long specialty, Date dateFrom);

    @Query(value = "select * from Appointment " +
            "inner join Doctor on Appointment.doctor_id = Doctor.id " +
            "inner join Specialty on Doctor.specialty_id = Specialty.id " +
            "where (Specialty.id = ?1) and (Appointment.date_time < ?2)", nativeQuery = true)
    public List<Appointment> findBySpecialtyIdAndDateTimeBefore(Long specialty, Date dateTo);


//   @Query(value = "UPDATE Appointments SET descr=CONCAT(descr, '?1') WHERE id=?2",nativeQuery = true)
////    public Appointment getAppointmentByUpdatedDescr(Appointment appointment);


}

