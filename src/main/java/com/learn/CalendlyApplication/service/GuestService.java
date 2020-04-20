package com.learn.CalendlyApplication.service;

import com.learn.CalendlyApplication.dto.SessionBookingDto;
import com.learn.CalendlyApplication.dto.SessionDto;
import com.learn.CalendlyApplication.model.Host;

import java.time.LocalDate;
import java.util.List;



public interface GuestService {
    List<Host> getAllHost();
    List<SessionDto> getSessionsForDayFromHost(Integer hostId, LocalDate sessionDate);
    void bookSessionForGuest(SessionBookingDto sessionBookingDto);
}
