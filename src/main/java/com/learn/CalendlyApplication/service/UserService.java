package com.learn.CalendlyApplication.service;

import com.learn.CalendlyApplication.model.User;

public interface UserService {
    User findUserByEmail(String email);
    User findUserByUsername(String userName);
    void saveUser(User user);
}
