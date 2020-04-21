package com.learn.CalendlyApplication.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learn.CalendlyApplication.dto.SessionDto;
import com.learn.CalendlyApplication.service.HostService;
import com.learn.CalendlyApplication.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("/host")
public class HostController {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private HostService hostService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUserDetails(@RequestParam String username) {
      String loggedInUsername = securityService.findLoggedInUsername();
      return loggedInUsername;
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public List<String> createHostSessions(@RequestParam Integer hostId,@NotNull @RequestBody List<SessionDto> sessionDtoList) {
        return hostService.addSession(hostId,sessionDtoList);
    }
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public SessionDto createHostSessions() {
        SessionDto sessionDto = new SessionDto();
        return sessionDto;
    }
}
