package com.artur.summer.backend.model;

import com.artur.summer.backend.constants.ClientStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientInfo {
    @Id
    private String uuid = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    private String middleName;
    private String login;
    private String secretHash;
    private String secretDate;
    private String address;
    private String certificateNumber;
    private LocalDateTime created;
    private ClientStatus status;
    private LocalDateTime updated;
}
