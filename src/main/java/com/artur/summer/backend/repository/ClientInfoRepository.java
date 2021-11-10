package com.artur.summer.backend.repository;

import com.artur.summer.backend.model.ClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientInfoRepository extends JpaRepository<ClientInfo, String> {
    Optional<ClientInfo> findAllByLogin(String login);

    ClientInfo getClientInfoByUuid(String uuid);
}
