package com.artur.summer.backend.service.impl;

import com.artur.summer.backend.constants.ProductType;
import com.artur.summer.backend.dto.ui.CardDto;
import com.artur.summer.backend.dto.ui.CardFullInfoDto;
import com.artur.summer.backend.dto.ui.ProductList;
import com.artur.summer.backend.dto.ui.ProductsDto;
import com.artur.summer.backend.factories.ProductAccountFactory;
import com.artur.summer.backend.factories.ProductCardFactory;
import com.artur.summer.backend.model.Account;
import com.artur.summer.backend.model.Card;
import com.artur.summer.backend.model.ClientInfo;
import com.artur.summer.backend.model.ProductInfo;
import com.artur.summer.backend.repository.AccountsRepository;
import com.artur.summer.backend.repository.CardsRepository;
import com.artur.summer.backend.repository.ProductInfoRepository;
import com.artur.summer.backend.security.ClientTokenService;
import com.artur.summer.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ClientTokenService tokenService;
    private final ProductInfoRepository productInfoRepository;
    private final CardsRepository cardsRepository;
    private final AccountsRepository accountsRepository;
    private final ModelMapper modelMapper;
    private final
    ProductCardFactory productCardFactory;
    private final ProductAccountFactory productAccountFactory;

    @Override
    public ProductsDto getAllActiveProductsShortInfo(String token) {
        Map<String, Pair<ProductType, String>> products = getProductMap(token);
        List<String> cardIds = products.entrySet().stream().filter(f -> f.getValue().getFirst().equals(ProductType.CARD)).map(Map.Entry::getKey).collect(Collectors.toList());
        List<CardDto> cardDtos = cardsRepository.findAllByUuidIn(cardIds).stream()
                .map(i -> {
                    CardDto cardDto = modelMapper.map(i, CardDto.class);
                    cardDto.setTitle(products.get(i.getUuid()).getSecond());
                    return cardDto;
                }).collect(Collectors.toList());

        return new ProductsDto(cardDtos, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    @Override
    public CardFullInfoDto getCardFullInfo(String token, String uuid) {
        ClientInfo clientInfoByToken = tokenService.getClientInfoByToken(token);
        ProductInfo productInfo = productInfoRepository.findAllByClientInfoAndProductUuid(clientInfoByToken, uuid);
        Card one = cardsRepository.getOne(productInfo.getProductUuid());
        Account account = accountsRepository.getOne(one.getAccountUuid());
        return productCardFactory.generate(one, productInfo, productAccountFactory.generate(account, productInfo.getAlias()));
    }

    @Override
    public List<ProductList> getCardList(String token) {
        Map<String, Pair<ProductType, String>> products = getProductMap(token);
        List<String> cardIds = products.entrySet().stream().filter(f -> f.getValue().getFirst().equals(ProductType.CARD)).map(Map.Entry::getKey).collect(Collectors.toList());
        List<ProductList> productLists = cardsRepository.findAllByUuidIn(cardIds).stream()
                .map(i -> new ProductList(i.getUuid(), "Карта " + i.getCardNumber(), i.getAmount())).collect(Collectors.toList());
        return productLists;
    }

    private Map<String, Pair<ProductType, String>> getProductMap(String token) {
        ClientInfo clientInfoByToken = tokenService.getClientInfoByToken(token);
        List<ProductInfo> allByClientInfo = productInfoRepository.findAllByClientInfo(clientInfoByToken);
        return allByClientInfo.stream().
                filter(prd -> prd.isActive() && prd.isVisible()).collect(Collectors.toMap(ProductInfo::getProductUuid, v -> Pair.of(v.getType(), v.getAlias())));
    }
}
