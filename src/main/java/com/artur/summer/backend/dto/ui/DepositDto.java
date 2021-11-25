package com.artur.summer.backend.dto.ui;

import com.artur.summer.backend.constants.Currency;

import java.math.BigDecimal;

public class DepositDto {
    private String uuid;
    private String accountNumber;
    private String title;
    private BigDecimal percent;
    private BigDecimal amount;
    private Currency currency;
}
