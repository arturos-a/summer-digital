package com.artur.summer.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Deposit extends Product {
    private String contractNumber;
    private String accountNumber;
    private BigDecimal percent;
    private LocalDateTime accountDate;
    private String accountBic;
}
