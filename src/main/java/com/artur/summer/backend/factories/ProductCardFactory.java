package com.artur.summer.backend.factories;

import com.artur.summer.backend.dto.ui.AccountProfileDto;
import com.artur.summer.backend.dto.ui.CardFullInfoDto;
import com.artur.summer.backend.model.Card;
import com.artur.summer.backend.model.ProductInfo;
import org.springframework.stereotype.Service;

@Service
public class ProductCardFactory {
    public CardFullInfoDto generate(Card card, ProductInfo productInfo, AccountProfileDto account) {
        CardFullInfoDto cardFullInfoDto = CardFullInfoDto.from(card.getUuid(),
                productInfo.getAlias(),
                card.getCardNumber(),
                card.getExpiredDate(),
                card.getCardOwner(),
                card.getAmount(),
                card.getCurrency(),
                account,
                card.isActive() && card.isActive()
        );
        return cardFullInfoDto;
    }
}
