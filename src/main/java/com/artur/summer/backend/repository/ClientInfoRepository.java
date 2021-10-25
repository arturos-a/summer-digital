package com.artur.summer.backend.repository;

import com.artur.summer.backend.model.ClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientInfoRepository extends JpaRepository<ClientInfo, String> {
    ClientInfo findAllByLogin(String login);
}
