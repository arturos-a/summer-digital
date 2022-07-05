package com.artur.summer.backend.model;

import com.artur.summer.backend.constants.ProductType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Setter
@Getter
public class ProductInfo {
    @Id
    private String uuid = UUID.randomUUID().toString();
    private String alias;
    @ManyToOne
    private ClientInfo clientInfo;
    private String productUuid;
    private boolean isActive = true;
    private boolean isVisible = true;
    private LocalDateTime created;
    @Enumerated(value = EnumType.STRING)
    private ProductType type;
}
