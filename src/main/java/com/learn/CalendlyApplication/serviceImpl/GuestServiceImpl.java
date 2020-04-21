package com.learn.CalendlyApplication.serviceImpl;

import com.learn.CalendlyApplication.dto.BookingDetailsDto;
import com.learn.CalendlyApplication.dto.HostDto;
import com.learn.CalendlyApplication.dto.SessionBookingDto;
import com.learn.CalendlyApplication.dto.SessionDto;
import com.learn.CalendlyApplication.model.Host;
import com.learn.CalendlyApplication.model.Session;
import com.learn.CalendlyApplication.repo.HostRepo;
import com.learn.CalendlyApplication.repo.SessionRepo;
import com.learn.CalendlyApplication.service.GuestService;
import com.learn.CalendlyApplication.service.SecurityService;
import com.learn.CalendlyApplication.util.CalendarUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private HostRepo hostRepo;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SessionRepo sessionRepo;

    @Override
    public List<HostDto> getAllHost() {
        List<Host>hostList = hostRepo.findAll();
        List<HostDto>hostDtoList = populateHostDtoFromHost(hostList);
        log.info("Total host : {} ",hostList.size());
        return hostDtoList;
    }

    @Override
    public List<SessionDto> getSessionsForDayFromHost(Integer hostId, LocalDate sessionDate) {
        String date = CalendarUtil.fromLocalDateToString(sessionDate);
        List<Session>sessionList = sessionRepo.findSessionByHostIdAndDate(hostId,date);
        List<SessionDto> sessionDtoList = getSessionDtoFromSessions(sessionList);
        log.info("Total sessionDTO : {} found for hostId : {} on date : {} ",sessionDtoList.size(),hostId,sessionDate);
        return sessionDtoList;
    }


    @Override
    @Transactional
    public synchronized Optional<BookingDetailsDto> bookSessionForGuest(SessionBookingDto sessionBookingDto) {
        Optional<Session>hostSession = validateBookingSession(sessionBookingDto);
        if(hostSession.isPresent()) {
            String loggedInUsername = securityService.findLoggedInUsername();
            BookingDetailsDto bookingDetailsDto = new BookingDetailsDto();
            bookingDetailsDto.setHostName(hostSession.get().getHost().getUser().getFirstName() + " " + hostSession.get().getHost().getUser().getLastName());
            bookingDetailsDto.setSessionDate(sessionBookingDto.getDate());
            bookingDetailsDto.setSessionTime(sessionBookingDto.getTime());
            bookingDetailsDto.setGuestName(loggedInUsername);
            log.info("Session booked for user {} with host : {} on date {} and time {}",bookingDetailsDto.getGuestName(),bookingDetailsDto.getHostName(),bookingDetailsDto.getSessionDate(),bookingDetailsDto.getSessionTime());
            return Optional.of(bookingDetailsDto);
        }
        else {
            log.info("current session is unavailable");
            return Optional.empty();
        }
    }
    private List<SessionDto> getSessionDtoFromSessions(List<Session>sessionList) {
        List<SessionDto>sessionDtoList = new ArrayList<>();
        for(Session session : sessionList) {
            LocalDate date = CalendarUtil.fromStringToLocalDate(session.getSessionDate());
            LocalTime time = CalendarUtil.fromStringToLocalTime(session.getSessionTime());
            SessionDto sessionDto = new SessionDto();
            sessionDto.setSessionDate(date);
            sessionDto.setSessionTime(time);
            sessionDtoList.add(sessionDto);
        }
        return sessionDtoList;
    }
    private Optional<Session> validateBookingSession(SessionBookingDto sessionBookingDto) {
        Boolean isValid = false;
        String localDate = CalendarUtil.fromLocalDateToString(sessionBookingDto.getDate());
        String localTime = CalendarUtil.fromLocalTimeToString(sessionBookingDto.getTime());
        Optional<Session>hostSession = sessionRepo.findByHostIdAndDateAndTime(sessionBookingDto.getHostId(),localDate,localTime);
        if(hostSession.isPresent() && hostSession.get().getIsBookable() == true) {
            return hostSession;
        }
        else {
            return Optional.empty();
        }
    }
    private List<HostDto>populateHostDtoFromHost(List<Host> hostList) {
        List<HostDto>hostDtoList = new ArrayList<>();
        for(Host host : hostList) {
            HostDto hostDto = new HostDto();
            hostDto.setHostId(host.getId());
            hostDto.setHostName(host.getUser().getFirstName() + " "+ host.getUser().getLastName());
            hostDto.setEmailId(host.getUser().getEmail());
            hostDtoList.add(hostDto);
        }
        return hostDtoList;
    }
}
