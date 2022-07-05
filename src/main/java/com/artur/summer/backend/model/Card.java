package com.artur.summer.backend.model;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card extends Product {
    private String cardNumber;
    private String expiredDate;
    private String cardOwner;
    private String accountUuid;
}
