package com.artur.summer.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Card extends Product {
    private String cardNumber;
    private String expiredDate;
    private String cardOwner;
    private String accountUuid;
}
