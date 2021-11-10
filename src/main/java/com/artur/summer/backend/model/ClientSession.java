package com.artur.summer.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
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
    private LocalDateTime expiredDate;
}
