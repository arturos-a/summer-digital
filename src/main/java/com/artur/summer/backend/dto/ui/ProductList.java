package com.artur.summer.backend.dto.ui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductList {
    private String uuid;
    private String text;
    private BigDecimal amount;
}
