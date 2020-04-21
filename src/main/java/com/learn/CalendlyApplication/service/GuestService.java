package com.learn.CalendlyApplication.service;

import com.learn.CalendlyApplication.dto.BookingDetailsDto;
import com.learn.CalendlyApplication.dto.HostDto;
import com.learn.CalendlyApplication.dto.SessionBookingDto;
import com.learn.CalendlyApplication.dto.SessionDto;
import com.learn.CalendlyApplication.model.Host;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface GuestService {
    List<HostDto> getAllHost();
    List<SessionDto> getSessionsForDayFromHost(Integer hostId, LocalDate sessionDate);
    Optional<BookingDetailsDto> bookSessionForGuest(SessionBookingDto sessionBookingDto);
}
