package com.artur.summer.backend.controller;

import com.artur.summer.backend.dto.AuthDTO;
import com.artur.summer.backend.security.ClientTokenService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import static com.artur.summer.backend.security.AuthFilter.X_AUTHORIZATION;

@RestController
public class LoginController {

    private final ClientTokenService clientTokenService;

    public LoginController(ClientTokenService clientTokenService) {
        this.clientTokenService = clientTokenService;
    }

    //User: SAI -  123456
    @PostMapping(value = "/auth")
    public String createAuthenticationToken(@RequestBody AuthDTO authDTO) throws Throwable {
        String token = clientTokenService.login(authDTO.getLogin(), authDTO.getSalt(), authDTO.getPassword());
        if (StringUtils.isEmpty(token)) {
            throw new UsernameNotFoundException("Не найдена комбинация пользователя и пароля");
        }
        return token;
    }

    @GetMapping(value = "/logout")
    public void logout(@RequestHeader(X_AUTHORIZATION) String sessionToken) {
        clientTokenService.logout(sessionToken);
    }

}
