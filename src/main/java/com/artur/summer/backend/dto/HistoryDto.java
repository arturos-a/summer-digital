package com.artur.summer.backend.dto;

import com.artur.summer.backend.constants.Currency;
import com.artur.summer.backend.constants.StatusCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDto {
    private String uuid;
    private String documentTitle;
    private BigDecimal amount;
    private Currency currency;
    private StatusCode status;
}
