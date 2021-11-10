package com.artur.summer.backend.security;

import com.artur.summer.backend.dto.AuthDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> authenticate(AuthDTO authDTO) throws Exception;
}
