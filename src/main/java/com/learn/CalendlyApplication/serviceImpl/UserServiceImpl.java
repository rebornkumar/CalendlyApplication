package com.learn.CalendlyApplication.serviceImpl;


import com.learn.CalendlyApplication.enums.UserType;
import com.learn.CalendlyApplication.model.Guest;
import com.learn.CalendlyApplication.model.Host;
import com.learn.CalendlyApplication.model.Role;
import com.learn.CalendlyApplication.model.User;
import com.learn.CalendlyApplication.repo.GuestRepo;
import com.learn.CalendlyApplication.repo.HostRepo;
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
    @Autowired
    private HostRepo hostRepo;
    @Autowired
    private GuestRepo guestRepo;

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
        userRepo.save(user);
        if(UserType.HOST.name().equalsIgnoreCase(user.getUserType())) {
            Role adminRole = new Role();
            adminRole.setRole("ROLE_ADMIN");
            roleRepo.save(adminRole);
            user.getRoles().add(adminRole);
            Host host = new Host();
            host.setUser(user);
            hostRepo.save(host);
        }
        else {
            Guest guest = new Guest();
            guest.setUser(user);
            guestRepo.save(guest);
        }
        userRepo.save(user);
    }
}
