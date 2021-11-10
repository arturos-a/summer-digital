package com.artur.summer.backend.security.impl;

import com.artur.summer.backend.dto.AuthDTO;
import com.artur.summer.backend.dto.AuthToken;
import com.artur.summer.backend.security.AuthService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;

    public AuthServiceImpl(@Qualifier("summer") UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<?> authenticate(AuthDTO authDTO) throws Exception {
        authenticate(authDTO.getLogin(), authDTO.getPassword());
        final String token = UUID.randomUUID().toString();
        return ResponseEntity.ok(new AuthToken(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
