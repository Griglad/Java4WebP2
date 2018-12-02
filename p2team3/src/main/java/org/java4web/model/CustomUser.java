package org.java4web.model;

import org.springframework.http.converter.json.MappingJacksonValue;

public interface CustomUser {
    String getUsername();

    String getPassword();

    void setFiltersForGetAppointments(MappingJacksonValue wrapper);
}
