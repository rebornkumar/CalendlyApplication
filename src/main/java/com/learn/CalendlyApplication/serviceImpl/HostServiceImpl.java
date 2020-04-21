package com.learn.CalendlyApplication.serviceImpl;

import com.learn.CalendlyApplication.dto.BasicResponse;
import com.learn.CalendlyApplication.dto.SessionDto;
import com.learn.CalendlyApplication.model.Host;
import com.learn.CalendlyApplication.model.Session;
import com.learn.CalendlyApplication.repo.HostRepo;
import com.learn.CalendlyApplication.repo.SessionRepo;
import com.learn.CalendlyApplication.service.HostService;
import com.learn.CalendlyApplication.util.CalendarUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class HostServiceImpl implements HostService {
    @Autowired
    private HostRepo hostRepo;
    @Autowired
    private SessionRepo sessionRepo;

    public List<String> addSession(Integer hostId,List<SessionDto> sessionDtoList) {
        List<String> errorMessages = new ArrayList<>();
        Optional<Host>host = hostRepo.findById(hostId);
        if(host.isPresent()) {
            for (SessionDto sessionDto : sessionDtoList) {
                String date = CalendarUtil.fromLocalDateToString(sessionDto.getSessionDate());
                String time = CalendarUtil.fromLocalTimeToString(sessionDto.getSessionTime());
                Optional<Session> oldSession = sessionRepo.findByHostIdAndDateAndTime(hostId, date, time);
                String message = "";
                if (oldSession.isPresent() == true) {
                    message = "For hostId : "+hostId+" , session with date : "+date+" and time : "+time+" already exist in database";
                    log.info("For hostId : {} , session with date : {} and time : {} already exist in database", hostId, date, time);
                } else {
                    Session session = createSession(host.get(),date,time);
                    sessionRepo.save(session);
                    message = "sessionId : "+session.getId()+" ,  hostId : "+hostId+" , session with date : "+date+" and time : "+time+"  saved to database successfully!!";
                    log.info("With sessionId : {} ,  hostId : {} , session with date : {} and time : {}  saved to database successfully!!",session.getId(), hostId, date, time);
                }
                errorMessages.add(message);
            }
        }
        else {
            errorMessages.add("host does not exist");
            log.error("host does not exist with hostId : ",hostId);
        }
        return errorMessages;
    }
    private Session createSession(Host host,String date,String time) {
        Session session = new Session();
        session.setHost(host);
        session.setSessionDate(date);
        session.setSessionTime(time);
        session.setIsBookable(true);
        return session;
    }
}
