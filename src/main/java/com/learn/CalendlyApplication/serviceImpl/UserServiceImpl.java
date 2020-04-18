package com.learn.CalendlyApplication.serviceImpl;


import com.learn.CalendlyApplication.model.User;
import com.learn.CalendlyApplication.repo.UserRepo;
import com.learn.CalendlyApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    public User findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    public User findUserByUsername(String userName) {
        return userRepo.findUserByUsername(userName);
    }

    public void saveUser(User user) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setActive(true);
        userRepo.save(user);
    }
}
