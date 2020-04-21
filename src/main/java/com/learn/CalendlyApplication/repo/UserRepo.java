package com.learn.CalendlyApplication.repo;

import com.learn.CalendlyApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);
}
