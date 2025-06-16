package dev.mordechai.studentcoursemanager.repository;

import dev.mordechai.studentcoursemanager.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    boolean existsBySessionKey(String sessionKey);
    Optional<Session> findBySessionKey(String sessionKey);

    void deleteBySessionKey(String sessionKey);

}