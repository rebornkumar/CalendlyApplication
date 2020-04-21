package com.learn.CalendlyApplication.controller;

import com.learn.CalendlyApplication.dto.BookingDetailsDto;
import com.learn.CalendlyApplication.dto.HostDto;
import com.learn.CalendlyApplication.dto.SessionBookingDto;
import com.learn.CalendlyApplication.dto.SessionDto;
import com.learn.CalendlyApplication.model.Host;
import com.learn.CalendlyApplication.service.GuestService;
import com.learn.CalendlyApplication.service.SecurityService;
import com.learn.CalendlyApplication.util.CalendarUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private GuestService guestService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUserDetails(@RequestParam String username) {
        String loggedInUsername = securityService.findLoggedInUsername();
        return loggedInUsername;
    }
    @RequestMapping(value = "/all/host", method = RequestMethod.GET)
    public List<HostDto> getAllHost() {
        return guestService.getAllHost();
    }

    @RequestMapping(value = "/get/session", method = RequestMethod.GET)
    public List<SessionDto> getAllSessionDayAndHostWise(@RequestParam Integer hostId, @RequestParam String localDate){
        List<SessionDto> sessionDtoList = new ArrayList<SessionDto>();
        LocalDate date = CalendarUtil.fromStringToLocalDate(localDate);
        sessionDtoList = guestService.getSessionsForDayFromHost(hostId,date);
       return sessionDtoList;
    }
    @RequestMapping(value = "/book/session", method = RequestMethod.POST)
    public Optional<BookingDetailsDto> bookSession(@Valid @RequestBody SessionBookingDto sessionBookingDto, Errors errors) throws Exception {
        if(errors.hasErrors()) {
            log.error("invalid input from user");
            return Optional.empty();
        }
        else {
            return guestService.bookSessionForGuest(sessionBookingDto);
        }
    }
    @RequestMapping(value = "/book/session", method = RequestMethod.GET)
    public SessionBookingDto bookSession() {
        SessionBookingDto sessionBookingDto = new SessionBookingDto();
        return sessionBookingDto;
    }
}
