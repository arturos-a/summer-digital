package com.artur.summer.backend.repository;

import com.artur.summer.backend.model.ClientSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientSessionRepository extends JpaRepository<ClientSession, String> {
    Optional<ClientSession> findClientSessionByUuid(String uuid);
}
