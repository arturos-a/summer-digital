package com.artur.summer.backend.dto.ui;

import com.artur.summer.backend.constants.Currency;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardFullInfoDto {
    private String uuid;
    private String title;
    private String cardNumber;
    private String expiredDate;
    private String cardOwner;
    private BigDecimal amount;
    private Currency currency;
    private AccountDto account;
    private Boolean isActive;

    public static CardFullInfoDto from(String uuid, String alias, String cardNumber, String expiredDate, String cardOwner, BigDecimal amount, Currency currency, AccountDto accountDto, boolean isActive) {
        CardFullInfoDto cardFullInfoDto = new CardFullInfoDto();
        cardFullInfoDto.setUuid(uuid);
        cardFullInfoDto.setTitle(alias);
        cardFullInfoDto.setCardNumber(cardNumber);
        cardFullInfoDto.setExpiredDate(expiredDate);
        cardFullInfoDto.setCardOwner(cardOwner);
        cardFullInfoDto.setAmount(amount);
        cardFullInfoDto.setCurrency(currency);
        cardFullInfoDto.setAccount(accountDto);
        cardFullInfoDto.setIsActive(isActive);
        return cardFullInfoDto;
    }
}
