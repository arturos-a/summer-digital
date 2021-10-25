package com.artur.summer.backend.model;

import com.artur.summer.backend.constants.ClientStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    private LocalDateTime secretDate;
    private String address;
    private String certificateNumber;
    private LocalDateTime created;
    @Enumerated(EnumType.STRING)
    private ClientStatus status;
    private LocalDateTime updated;

    public ClientInfo(ClientInfo clientInfoFrom) {
        this.uuid = clientInfoFrom.getUuid();
        this.firstName = clientInfoFrom.getFirstName();
        this.lastName = clientInfoFrom.getLastName();
        this.middleName = clientInfoFrom.getMiddleName();
        this.secretHash = clientInfoFrom.getSecretHash();
        this.secretDate = clientInfoFrom.getSecretDate();
        this.address = clientInfoFrom.getAddress();
        this.certificateNumber = clientInfoFrom.getCertificateNumber();
        this.created = clientInfoFrom.getCreated();
        this.status = clientInfoFrom.getStatus();
        this.updated = clientInfoFrom.getUpdated();
        this.login = clientInfoFrom.getLogin();
    }
}
