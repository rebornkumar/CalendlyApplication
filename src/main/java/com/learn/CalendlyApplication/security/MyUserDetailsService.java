package com.learn.CalendlyApplication.security;

import com.learn.CalendlyApplication.model.User;
import com.learn.CalendlyApplication.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findUserByUsername(username);
        optionalUser.orElseThrow(()-> new UsernameNotFoundException("UserName Not Found"));
        UserDetails userDetails = new MyUserDetails(optionalUser.get());
        return userDetails;
    }
}
