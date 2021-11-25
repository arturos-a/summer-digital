package com.artur.summer.backend.dto.ui;

import com.artur.summer.backend.constants.Currency;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreditDto {
    private String uuid;
    private String creditTitle;
    private BigDecimal amount;
    private Currency currency;
}
