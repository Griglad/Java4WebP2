package org.java4web.controllers;

import org.java4web.model.Appointment;
import org.java4web.services.AppointmentService;
import org.java4web.utilities.AppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments/{id}")
    public MappingJacksonValue getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointment(id);
        return appointmentService.createMJVAppointment(appointment, true);
    }

    @GetMapping("/patients/appointments")
    public MappingJacksonValue getPatientAppointments(@RequestParam(value = "spec", required = false) String specialtyName,
                                                      @RequestParam(value = "from", required = false) String dateFrom,
                                                      @RequestParam(value = "to", required = false) String dateTo) {
        List<Appointment> appointments = appointmentService.getPatientAppointments(specialtyName, dateFrom, dateTo);
        return appointmentService.createMJVAppointments(appointments, false);
    }

    @GetMapping("/doctors/appointments")
    public MappingJacksonValue getDoctorAppointments(@RequestParam(value = "descr", required = false) String descr,
                                                     @RequestParam(value = "from", required = false) String dateFrom,
                                                     @RequestParam(value = "to", required = false) String dateTo) {
        List<Appointment> appointments = appointmentService.getDoctorAppointments(descr, dateFrom, dateTo);
        return appointmentService.createMJVAppointments(appointments, false);
    }

    @PostMapping("/appointments")
    public MappingJacksonValue newAppointment(@RequestBody @Valid AppointmentDto appointmentDto, Principal principal) {
        Appointment appointment = appointmentService.newAppointment(appointmentDto, principal);
        return appointmentService.createMJVAppointment(appointment, true);
    }

    @PutMapping("/appointments/{id}")
    public MappingJacksonValue updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentDto updatedAppointmentDto, Principal principal) {
        Appointment appointment = appointmentService.updateAppointment(id, updatedAppointmentDto, principal);
        return appointmentService.createMJVAppointment(appointment, true);
    }

    @DeleteMapping("/appointments/{id}")
    public void deleteAppointmentById(@PathVariable Long id) {

        Appointment appointment = appointmentService.getAppointment(id);
        appointmentService.deleteAppointment(appointment);
    }
}
