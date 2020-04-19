package com.learn.CalendlyApplication.repo;

import com.learn.CalendlyApplication.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepo extends JpaRepository<Host,Integer> {
}
