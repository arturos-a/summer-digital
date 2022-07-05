package com.artur.summer.backend.factories;

import com.artur.summer.backend.dto.ui.AccountProfileDto;
import com.artur.summer.backend.model.Account;
import org.springframework.stereotype.Service;

@Service
public class ProductAccountFactory {
    public AccountProfileDto generate(Account account, String title) {
        return AccountProfileDto.from(account.getUuid(),
                account.getBankKpp(),
                account.getCorrAccountNumber(),
                account.getBankName(),
                account.getAccountNumber(),
                account.getAmount(),
                account.getCurrency(),
                title

        );
    }
}
