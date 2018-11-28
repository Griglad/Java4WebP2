package org.java4web.controllers;


import org.java4web.exceptions.AppointmentNotFoundException;
import org.java4web.model.Appointment;
import org.java4web.repositories.AppointmentRepository;
import org.java4web.services.AppointmentService;
import org.java4web.utils.AppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping("/appointments")
    public Appointment newAppointment(@RequestBody @Valid AppointmentDto appointmentDto, Principal principal){


        return appointmentService.newAppointment(appointmentDto,principal);

    }

//    @GetMapping("/appointments/{id}")
//    public Appointment getAppointmentById(@PathVariable Long id) {
//
//            return  appointment.findById(id)
//                .orElseThrow(() -> new AppointmentNotFoundException(id));
//    }
//
//    @GetMapping("/appointments")
//    public List<Appointment>  getAppointmentsFromTo(@RequestParam String from,@RequestParam String to){
//        long id=1;
//
//        return appointmentRepository.fin(id);
//    }


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
