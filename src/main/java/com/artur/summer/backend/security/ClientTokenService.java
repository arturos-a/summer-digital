package com.artur.summer.backend.security;

import com.artur.summer.backend.model.ClientInfo;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ClientTokenService {
    String login(String username, String salt, String password) throws Throwable;

    void logout(String token);

    Optional<UserDetails> findByToken(String token);

    ClientInfo getClientInfoByToken(String token);
}
