package com.learn.CalendlyApplication.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

public interface SecurityService {
    String findLoggedInUsername();
    String autoLogin(String username, String password) throws BadCredentialsException;
}
