package com.artur.summer.backend.dto.api;

import com.artur.summer.backend.constants.Currency;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BsAccount {
    private String uuid;
    private String accountNumber;
    private LocalDateTime accountDate;
    private String bankBic;
    private String bankKpp;
    private String corrAccountNumber;
    private String bankName;
    private boolean isActive;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private String clientUuid;
}
