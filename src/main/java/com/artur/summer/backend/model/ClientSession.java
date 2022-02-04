package com.artur.summer.backend.model;

import com.artur.summer.backend.constants.SessionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientSession {
    @Id
    private String uuid;
    private String clientUuid;
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime updated;
    private LocalDateTime expiredDate;
    @Enumerated(value = EnumType.STRING)
    private SessionStatus sessionStatus;
}
