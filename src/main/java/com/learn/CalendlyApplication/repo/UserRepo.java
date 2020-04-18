package com.learn.CalendlyApplication.repo;

import com.learn.CalendlyApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
