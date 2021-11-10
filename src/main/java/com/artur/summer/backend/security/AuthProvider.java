package com.artur.summer.backend.security;

import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthProvider extends AbstractUserDetailsAuthenticationProvider {
    private ClientTokenService clientTokenService;

    public AuthProvider(ClientTokenService clientTokenService) {
        this.clientTokenService = clientTokenService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @SneakyThrows
    @Override
    protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        Object token = usernamePasswordAuthenticationToken.getCredentials();
        return Optional
                .ofNullable(token)
                .map(String::valueOf)
                .flatMap(clientTokenService::findByToken)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
    }
}
