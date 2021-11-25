package com.artur.summer.backend.dto.ui;

import com.artur.summer.backend.constants.Currency;

import java.math.BigDecimal;

public class AccountDto {
    private String uuid;
    private String title;
    private String accountNumber;
    private BigDecimal amount;
    private Currency currency;
}
