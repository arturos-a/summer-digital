package com.artur.summer.backend.service.api.impl;

import com.artur.summer.backend.dto.api.BsAccount;
import com.artur.summer.backend.model.Account;
import com.artur.summer.backend.repository.AccountsRepository;
import com.artur.summer.backend.service.api.BsApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BsApiClientDemoImpl implements BsApiClient {
    private final AccountsRepository accountsRepository;

    @Override
    public BsAccount getAccountByUuid(String uuid) {
        Account account = accountsRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Не найден счет " + uuid));
        BsAccount bsAccount = new BsAccount();
        bsAccount.setUuid(account.getUuid());
        bsAccount.setBankBic(account.getBankBic());
        bsAccount.setBankKpp(account.getBankKpp());
        bsAccount.setBankName(account.getBankName());
        bsAccount.setAmount(account.getAmount());
        bsAccount.setCurrency(account.getCurrency());
        bsAccount.setAccountNumber(account.getAccountNumber());
        bsAccount.setClientUuid(account.getOwnerUuid());
        return bsAccount;
    }
}
