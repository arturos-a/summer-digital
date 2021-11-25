package com.artur.summer.backend.model;

import com.artur.summer.backend.constants.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String uuid = UUID.randomUUID().toString();
    private boolean isActive;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private LocalDateTime created;
    private LocalDateTime updated;
    @OneToMany(mappedBy = "product")
    private Set<ProductInfo> productInfo;
}
