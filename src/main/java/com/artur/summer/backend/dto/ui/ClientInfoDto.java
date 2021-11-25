package com.artur.summer.backend.dto.ui;

import lombok.Data;

@Data
public class ClientInfoDto {
    private String firstName;
    private String lastName;
    private String middleName;
    private String login;
    private String address;
    private String certificateNumber;
}
