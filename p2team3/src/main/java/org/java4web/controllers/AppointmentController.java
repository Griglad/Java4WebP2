package org.java4web.controllers;


import org.java4web.exceptions.AppointmentNotFoundException;
import org.java4web.model.Appointment;
import org.java4web.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AppointmentController {


    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }


    @PostMapping("/appointments")
    public Appointment newAppointment(@RequestBody Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    @GetMapping("/appointments/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {

            return  appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(id));
    }

    @GetMapping("/appointments")
    public List<Appointment>  getAppointmentsFromTo(@RequestParam String from,@RequestParam String to){
        long id=1;

        return appointmentRepository.fin(id);
    }
/*
    @PutMapping("/appointments/{id}")
    public Appointment updateAppointment(@PathVariable Long id,@RequestBody Appointment updatedAppointment){


    }

    @DeleteMapping("appointments/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable Long id) {
        getAppointment(id);
        appointmentRepository.deleteById(id);
    }
*/










}
