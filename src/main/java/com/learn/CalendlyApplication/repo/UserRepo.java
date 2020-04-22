package com.learn.CalendlyApplication.repo;

import com.learn.CalendlyApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    @Query("SELECT user FROM User user JOIN FETCH user.roles WHERE user.username = ?1")
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);
}
