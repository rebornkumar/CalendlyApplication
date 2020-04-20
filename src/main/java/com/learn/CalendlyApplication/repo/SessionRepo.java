package com.learn.CalendlyApplication.repo;

import com.learn.CalendlyApplication.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepo extends JpaRepository<Session,Integer> {
    @Query(value = "SELECT * FROM session JOIN host ON (host.id = session.host_id) where host.id = ?1 AND session.date = ?2 AND session.time = ?3",nativeQuery = true)
    Optional<Session> findByHostIdAndDateAndTime(Integer hostId, String date, String time);

    @Query(value = "SELECT * FROM session JOIN host ON (host.id = session.host_id) where host.id = ?1 AND session.date = ?2",nativeQuery = true)
    List<Session> findAllByHostIdAndDate(Integer hostId, LocalDate sessionDate);
}
