package com.artur.summer.backend.dto.ui;

import com.artur.summer.backend.constants.Currency;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountProfileDto extends AccountDto {
    private String title;

    public static AccountProfileDto from(String uuid, String bankKpp, String corrAccountNumber, String bankName, String accountNumber, BigDecimal amount, Currency currency, String title) {
        AccountProfileDto accountDto = new AccountProfileDto();
        accountDto.setUuid(uuid);
        accountDto.setBankKpp(bankKpp);
        accountDto.setCorrAccountNumber(corrAccountNumber);
        accountDto.setBankName(bankName);
        accountDto.setAccountNumber(accountNumber);
        accountDto.setAmount(amount);
        accountDto.setCurrency(currency);
        accountDto.setTitle(title);
        return accountDto;
    }
}
