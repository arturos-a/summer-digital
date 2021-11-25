package com.artur.summer.backend.dto.ui;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductsDto {
    private List<CardDto> cards;
    private List<AccountDto> accounts;
    private List<DepositDto> deposits;
    private List<CreditDto> credits;

}
