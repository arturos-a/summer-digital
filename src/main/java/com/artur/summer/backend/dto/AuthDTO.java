package com.artur.summer.backend.dto;

import lombok.Data;

@Data
public class AuthDTO {
    private String login;
    private String salt;
    private String password;
}
