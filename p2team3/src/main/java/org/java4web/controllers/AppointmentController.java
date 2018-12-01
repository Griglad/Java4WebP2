package org.java4web.controllers;

import org.java4web.services.AppointmentService;
import org.java4web.utils.AppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments/{id}")
    public MappingJacksonValue getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/patients/appointments")
    public MappingJacksonValue getPatientAppointments(@RequestParam(value = "spec", required = false) String specialtyName,
                                              @RequestParam(value = "from", required = false) String dateFrom,
                                              @RequestParam(value = "to", required = false) String dateTo) {
        return appointmentService.getPatientAppointments(specialtyName, dateFrom, dateTo);
    }

    @GetMapping("/doctors/appointments")
    public MappingJacksonValue getDoctorAppointments(@RequestParam(value = "descr", required = false) String description,
                                                      @RequestParam(value = "from", required = false) String dateFrom,
                                                      @RequestParam(value = "to", required = false) String dateTo) {
        return appointmentService.getDoctorAppointments(description, dateFrom, dateTo);
    }

    @PostMapping("/appointments")
    public MappingJacksonValue newAppointment(@RequestBody @Valid AppointmentDto appointmentDto, Principal principal) {
        return appointmentService.newAppointment(appointmentDto, principal);
    }

    @PutMapping("/appointments/{id}")
    public MappingJacksonValue updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentDto updatedAppointmentDto, Principal principal) {
        return appointmentService.updateAppointment(id, updatedAppointmentDto, principal);
    }

    @DeleteMapping("/appointments/{id}")
    public void deleteAppointmentById(@PathVariable Long id) {
        appointmentService.deleteAppointmentById(id);
    }
}
