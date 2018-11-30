package org.java4web.services;

import org.java4web.exceptions.DoctorNotFoundException;
import org.java4web.model.*;
import org.java4web.repositories.AppointmentRepository;
import org.java4web.repositories.DoctorRepository;
import org.java4web.repositories.PatientRepository;
import org.java4web.repositories.SpecialtyRepository;
import org.java4web.security.CustomUserDetails;
import org.java4web.utils.AppointmentDto;
import org.java4web.utils.Utils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final SpecialtyRepository specialtyRepository;


    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository,
                              DoctorRepository doctorRepository, SpecialtyRepository specialtyRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.specialtyRepository = specialtyRepository;

    }

    public MappingJacksonValue newAppointment(@Valid AppointmentDto appointmentDto, Principal principal) {
        Appointment entityAppointment = new Appointment();


        Patient patient = patientRepository.findByUsername(principal.getName());
        entityAppointment.setPatient(patient);
        Optional<Doctor> doctorOptional = doctorRepository.findById(appointmentDto.getDoctorId());

        if (doctorOptional == null) {
            throw new DoctorNotFoundException(appointmentDto.getDoctorId().toString());
        }
        Doctor doctor = doctorOptional.get();
        entityAppointment.setDoctor(doctor);
        entityAppointment.setDateTime(Utils.dateFormatParse(appointmentDto.getDate()));
        entityAppointment.setDescr(appointmentDto.getDescr());
        entityAppointment.setNotes(appointmentDto.getNotes());

        return createMJVforGetAppointment(appointmentRepository.save(entityAppointment), principal);
    }

    public MappingJacksonValue getAppointments(Principal principal, String specialtyIdStr, String dateFrom, String dateTo) {

        if (specialtyIdStr != null) {
            Long specialtyId = Long.valueOf(specialtyIdStr);
            Optional<Specialty> specialty = specialtyRepository.findById(specialtyId);
            if (dateFrom == null && dateTo == null) {
                //TODO: Catch Long parsing.
                if (specialty.isPresent()) {
                    return createMJVforGetAppointments((appointmentRepository.findBySpecialtyId(specialty.get().getId())),principal);
                }
                return null;
            } else if (dateFrom != null && dateTo != null) {
                Date fromDate = Utils.dateFormatParse(dateFrom);
                Date toDate = Utils.dateFormatParse(dateTo);
                return createMJVforGetAppointments((appointmentRepository.findBySpecialtyIdAndDateTimeBetween(specialtyId,fromDate, toDate)), principal);
            } else {
                //TODO:
                return null;
            }
        } else if (dateFrom != null && dateTo != null) {
            Date fromDate = Utils.dateFormatParse(dateFrom);
            Date toDate = Utils.dateFormatParse(dateTo);
            return createMJVforGetAppointments(appointmentRepository.findByDateTimeBetween(fromDate, toDate), principal);
        }else{
            return null;
        }
    }

    MappingJacksonValue createMJVforGetAppointments(List<Appointment> appointments, Principal principal){
        return applyMJVAppointmentsFilters(new MappingJacksonValue(appointments),principal);
    }

    MappingJacksonValue createMJVforGetAppointment(Appointment appointment, Principal principal){
        return applyMJVAppointmentsFilters(new MappingJacksonValue(appointment),principal);
    }

    MappingJacksonValue applyMJVAppointmentsFilters(MappingJacksonValue wrapper, Principal principal){
        CustomUserDetails userDetails = (CustomUserDetails) principal;
        if(wrapper!=null){
            userDetails.getUser().setFiltersForGetAppointments(wrapper);
        }
        return wrapper;
    }
}



