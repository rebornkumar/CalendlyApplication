package com.learn.CalendlyApplication.service;

import com.learn.CalendlyApplication.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String userName);
    void saveUser(User user);
}
