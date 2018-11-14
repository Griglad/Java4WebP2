package org.java4web.controllers;


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

/*
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }


    @PostMapping("/appointments")
    public Appointment newAppointment(@RequestBody Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    @GetMapping("/appointments{id}")
    public Appointment getAppointment(@PathVariable Long id) {
        return appointmentRepository.findById(id);
    }

    @GetMapping("/appointments")
    public List<Appointment>  getAppointmentsFromTo(@RequestParam Date from,@RequestParam Date to){
        return appointmentRepository.findFromTo(pID,from,to);
    }

    @PutMapping("/appointments{id}")
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
