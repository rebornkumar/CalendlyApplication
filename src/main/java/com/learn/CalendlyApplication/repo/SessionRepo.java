package com.learn.CalendlyApplication.repo;

import com.learn.CalendlyApplication.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<Session,Integer> {
}
