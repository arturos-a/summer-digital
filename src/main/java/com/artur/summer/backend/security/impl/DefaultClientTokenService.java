package com.artur.summer.backend.security.impl;

import com.artur.summer.backend.model.ClientInfo;
import com.artur.summer.backend.model.ClientSession;
import com.artur.summer.backend.repository.ClientInfoRepository;
import com.artur.summer.backend.repository.ClientSessionRepository;
import com.artur.summer.backend.security.ClientTokenService;
import com.artur.summer.backend.security.HashProvider;
import liquibase.util.StringUtils;
import lombok.SneakyThrows;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultClientTokenService implements ClientTokenService {
    private static final long SESSION_LIFETIME = 15;
    private HashProvider hashProvider;
    private ClientInfoRepository clientInfoRepository;
    private ClientSessionRepository clientSessionRepository;

    public DefaultClientTokenService(HashProvider hashProvider, ClientInfoRepository clientInfoRepository, ClientSessionRepository clientSessionRepository) {
        this.hashProvider = hashProvider;
        this.clientInfoRepository = clientInfoRepository;
        this.clientSessionRepository = clientSessionRepository;
    }

    @Override
    public String login(String username, String salt, String password) throws Throwable {
        if (StringUtils.isEmpty(salt)) {
            throw new RuntimeException("Unsalted password");
        }
        boolean authResult;
        ClientInfo clientInfo = clientInfoRepository.findAllByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Пользователь " + username + " не найден."));
        String saltedPassword = salt + clientInfo.getSecretHash();
        byte[] saltedHashedPassword = hashProvider.getHash(saltedPassword);
        authResult = MessageDigest.isEqual(saltedHashedPassword, password.getBytes(StandardCharsets.UTF_8));
        ClientSession clientSession = generateToken(clientInfo.getUuid());
        if (authResult) {
            clientSessionRepository.save(clientSession);
        }
        return clientSession.getUuid();
    }

    @Override
    @SneakyThrows
    public Optional<UserDetails> findByToken(String token) {
        Optional<ClientSession> clientSession = clientSessionRepository.findClientSessionByUuid(token);
        if (clientSession.isPresent()) {
            ClientSession session = clientSession.get();
            boolean isNonExpired = true;
            ClientInfo clientInfo = clientInfoRepository.getClientInfoByUuid(session.getClientUuid());
            if (clientInfo == null) {
                return Optional.empty();
            }
            if (session.getExpiredDate().isBefore(LocalDateTime.now())) {
                isNonExpired = false;
            } else {
                session.setExpiredDate(LocalDateTime.now());
                clientSessionRepository.save(session);
            }
            User user = new User(clientInfo.getLogin(), clientInfo.getSecretHash(), true, true, isNonExpired, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return Optional.empty();
    }

    private ClientSession generateToken(String clientUuid) {
        if (StringUtils.isEmpty(clientUuid)) {
            throw new UsernameNotFoundException("Невозможно выполнить авторизацию. Пользователь не найден");
        }
        ClientSession clientSession = new ClientSession();
        clientSession.setClientUuid(clientUuid);
        clientSession.setExpiredDate(LocalDateTime.now().plusMinutes(SESSION_LIFETIME));
        clientSession.setCreated(LocalDateTime.now());
        clientSession.setUuid(UUID.randomUUID().toString());
        return clientSession;
    }
}
