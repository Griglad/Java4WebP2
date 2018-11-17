package org.java4web.repositories;

import org.java4web.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query(value ="select * from appointment where id = :?1", nativeQuery = true)
    List<Appointment> fin(Long id);
}

