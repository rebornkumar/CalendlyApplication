package com.learn.CalendlyApplication.controller;


import com.learn.CalendlyApplication.model.User;
import com.learn.CalendlyApplication.service.SecurityService;
import com.learn.CalendlyApplication.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String autoLogin(@RequestParam String username, @RequestParam String password) {
        securityService.autoLogin(username,password);
        return "login successful";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration",method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView createNewUser(@Valid  User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> userExists = userService.findUserByUsername(user.getUsername());
        if (userExists.isPresent()) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the user name provided");
            log.info("user already exists in the system");
        }
        else if(bindingResult.hasErrors()) {
            log.error("Something went wrong");
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            log.info("User is saved to DB successfully with UserId : {}", user.getId());
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/home",method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findUserByUsername(auth.getName());
        modelAndView.addObject("username", "Welcome " + user.get().getUsername() + "/" + user.get().getFirstName() + " " + user.get().getLastName() + " (" + user.get().getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
}

