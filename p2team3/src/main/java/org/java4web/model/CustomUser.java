package org.java4web.model;

import org.springframework.http.converter.json.MappingJacksonValue;

public interface CustomUser {
    public String getUsername();

    public String getPassword();

    public void setFiltersForGetAppointments(MappingJacksonValue wrapper);
}
