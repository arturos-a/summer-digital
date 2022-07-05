package com.artur.summer.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Document {
    private String srcAccountUuid;
    private String srcAccountNumber;
    private String srcBankBic;
    private String srcBankName;
    private String srcCorrAccountNumber;
    private String srcBankKpp;
    private BigDecimal amount;
    private String clientUuid;
}
