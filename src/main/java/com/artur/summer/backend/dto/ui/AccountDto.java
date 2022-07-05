package com.artur.summer.backend.dto.ui;

import com.artur.summer.backend.constants.Currency;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {
    private String uuid;
    private String bankKpp;
    private String corrAccountNumber;
    private String bankName;
    private String accountNumber;
    private BigDecimal amount;
    private Currency currency;

    public static AccountDto from(String uuid, String bankKpp, String corrAccountNumber, String bankName, String accountNumber, BigDecimal amount, Currency currency) {
        AccountDto accountDto = new AccountDto();
        accountDto.setUuid(uuid);
        accountDto.setBankKpp(bankKpp);
        accountDto.setCorrAccountNumber(corrAccountNumber);
        accountDto.setBankName(bankName);
        accountDto.setAccountNumber(accountNumber);
        accountDto.setAmount(amount);
        accountDto.setCurrency(currency);
        return accountDto;
    }
}
