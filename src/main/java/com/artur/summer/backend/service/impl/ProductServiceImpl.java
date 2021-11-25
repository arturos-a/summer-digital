package com.artur.summer.backend.service.impl;

import com.artur.summer.backend.dto.ui.CardDto;
import com.artur.summer.backend.dto.ui.ProductsDto;
import com.artur.summer.backend.model.Card;
import com.artur.summer.backend.model.ClientInfo;
import com.artur.summer.backend.model.Product;
import com.artur.summer.backend.model.ProductInfo;
import com.artur.summer.backend.repository.ProductInfoRepository;
import com.artur.summer.backend.security.ClientTokenService;
import com.artur.summer.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ClientTokenService tokenService;
    private final ProductInfoRepository productInfoRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductsDto getAllActiveProductsShortInfo(String token) {
        ClientInfo clientInfoByToken = tokenService.getClientInfoByToken(token);
        List<ProductInfo> allByClientInfo = productInfoRepository.findAllByClientInfo(clientInfoByToken);
        List<Pair<Product, String>> products = allByClientInfo.stream().filter(prd -> prd.isActive() && prd.isVisible()).map(prd -> Pair.of(prd.getProduct(), prd.getAlias())).collect(Collectors.toList());
        List<CardDto> cards = products.stream().filter(i -> i.getFirst() instanceof Card).map(i -> {
            CardDto cardDto = modelMapper.map(i.getFirst(), CardDto.class);
            cardDto.setTitle(i.getSecond());
            return cardDto;
        }).collect(Collectors.toList());

        return new ProductsDto(cards, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }
}
