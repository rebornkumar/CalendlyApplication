package com.learn.CalendlyApplication.controller;

import com.learn.CalendlyApplication.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUserDetails(@RequestParam String username) {
        String loggedInUsername = securityService.findLoggedInUsername();
        return loggedInUsername;
    }
}
