package com.artur.summer.backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    @ManyToOne
    private Product product;
    private boolean isActive = true;
    private boolean isVisible = true;
    private LocalDateTime created;
}
