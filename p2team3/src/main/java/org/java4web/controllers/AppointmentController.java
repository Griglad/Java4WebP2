package org.java4web.controllers;


import org.java4web.exceptions.AppointmentNotFoundException;
import org.java4web.model.Appointment;
import org.java4web.services.AppointmentService;
import org.java4web.utils.AppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.util.List;

@RestController
public class AppointmentController {


    private final AppointmentService appointmentService;


    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;

    }

    @GetMapping("/appointments")
    public List<Appointment> getAppointments(@RequestParam(value = "spec", required = false) String specialtyName,
                                             @RequestParam(value = "from", required = false) String dateFrom,
                                             @RequestParam(value = "to", required = false) String dateTo, Principal principal) {
        return appointmentService.getAppointments(principal, specialtyName, dateFrom, dateTo);


    }

    @PostMapping("/appointments")
    public Appointment newAppointment(@RequestBody @Valid AppointmentDto appointmentDto, Principal principal) {


        return appointmentService.newAppointment(appointmentDto, principal);

    }

    @GetMapping("/appointments/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {

        return appointmentService.getAppointmentById(id);

    }


    @PutMapping("/appointments/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentDto updatedAppointmentDto, Principal principal) {
        return appointmentService.updateAppointment(id, updatedAppointmentDto, principal);
    }


    @DeleteMapping("appointments/{id}")
    public void deleteAppointmentById(@PathVariable Long id) {
        appointmentService.deleteAppointmentById(id);
    }


}
