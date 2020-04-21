package com.learn.CalendlyApplication.serviceImpl;


import com.learn.CalendlyApplication.enums.UserType;
import com.learn.CalendlyApplication.model.Role;
import com.learn.CalendlyApplication.model.User;
import com.learn.CalendlyApplication.repo.RoleRepo;
import com.learn.CalendlyApplication.repo.UserRepo;
import com.learn.CalendlyApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    public Optional<User> findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    public Optional<User> findUserByUsername(String userName) {
        return userRepo.findUserByUsername(userName);
    }

    public void saveUser(User user) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setActive(true);
        Role role = new Role();
        role.setRole("ROLE_USER");
        roleRepo.save(role);
        user.getRoles().add(role);
        if(UserType.HOST.name().equalsIgnoreCase(user.getUserType())) {
            Role adminRole = new Role();
            adminRole.setRole("ROLE_ADMIN");
            roleRepo.save(adminRole);
            user.getRoles().add(adminRole);
        }
        userRepo.save(user);
    }
}
