package com.artur.summer.backend.service;

import com.artur.summer.backend.dto.ui.CardFullInfoDto;
import com.artur.summer.backend.dto.ui.ProductList;
import com.artur.summer.backend.dto.ui.ProductsDto;
import com.artur.summer.backend.model.Product;

import java.util.List;

public interface ProductService {
    ProductsDto getAllActiveProductsShortInfo(String token);

    CardFullInfoDto getCardFullInfo(String token, String uuid);

    List<ProductList> getCardList(String session);
}
