package com.artur.summer.backend.repository;

import com.artur.summer.backend.model.ClientInfo;
import com.artur.summer.backend.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    List<ProductInfo> findAllByClientInfo(ClientInfo clientInfo);
}
