package com.artur.summer.backend.security.impl;

import com.artur.summer.backend.constants.SessionStatus;
import com.artur.summer.backend.exception.SessionExpiredException;
import com.artur.summer.backend.exception.WrongUsernameOrPassword;
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
        } else {
            throw new WrongUsernameOrPassword();
        }
        return clientSession.getUuid();
    }

    @Override
    @SneakyThrows
    public Optional<UserDetails> findByToken(String token) {
        if (token == null) throw new SessionExpiredException();
        Optional<ClientSession> clientSession = clientSessionRepository.findClientSessionByUuid(token);
        if (clientSession.isPresent()) {
            ClientSession session = clientSession.get();
            boolean isNonExpired = true;
            ClientInfo clientInfo = clientInfoRepository.getClientInfoByUuid(session.getClientUuid());
            if (clientInfo == null) {
                return Optional.empty();
            }
            if (session.getSessionStatus() != null && (session.getSessionStatus().equals(SessionStatus.EXP) || session.getExpiredDate().plusMinutes(SESSION_LIFETIME).isBefore(LocalDateTime.now()))) {
                isNonExpired = false;
                session.setExpiredDate(LocalDateTime.now());
                session.setSessionStatus(SessionStatus.EXP);
                clientSessionRepository.save(session);
            }
            User user = new User(clientInfo.getLogin(), clientInfo.getSecretHash(), true, true, isNonExpired, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        throw new WrongUsernameOrPassword();
    }

    @Override
    public ClientInfo getClientInfoByToken(String token) {
        ClientInfo clientInfo = null;
        Optional<ClientSession> clientSession = clientSessionRepository.findClientSessionByUuid(token);
        if (clientSession.isPresent()) {
            clientInfo = clientInfoRepository.getClientInfoByUuid(clientSession.get().getClientUuid());
        } else {
            throw new RuntimeException("Клиент не найден");
        }
        return clientInfo;
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
        clientSession.setSessionStatus(SessionStatus.ACT);
        return clientSession;
    }

    public void logout(String token) {
        Optional<ClientSession> clientSessionByUuid = clientSessionRepository.findClientSessionByUuid(token);
        clientSessionByUuid.ifPresent(impl -> {
            impl.setSessionStatus(SessionStatus.DEL);
            impl.setUpdated(LocalDateTime.now());
        });
    }
}
