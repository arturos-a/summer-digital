package com.artur.summer.backend.dto.ui;

import com.artur.summer.backend.constants.Currency;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CardDto {
    private String uuid;
    private String title;
    private String cardNumber;
    private String expiredDate;
    private String cardOwner;
    private BigDecimal amount;
    private Currency currency;
}
