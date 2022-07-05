package com.artur.summer.backend.service.bscontrol.impl;

import com.artur.summer.backend.dto.api.BsAccount;
import com.artur.summer.backend.exception.ControlException;
import com.artur.summer.backend.service.api.BsApiClient;
import com.artur.summer.backend.service.bscontrol.AccountControl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

import static com.artur.summer.backend.constants.DocumentFieldsNames.AMOUNT;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountAmountControl implements AccountControl {
    private final BsApiClient bsApiClient;

    @Override
    public boolean check(String accountUuid, Map<String, String> checkParams) {
        try {
            BsAccount bsAccount = bsApiClient.getAccountByUuid(accountUuid);
            return new BigDecimal(checkParams.get(AMOUNT)).compareTo(bsAccount.getAmount()) <= 0;
        } catch (Exception ex) {
            log.error("Ошибка", ex);
            throw new ControlException(ex.toString());
        }
    }

    @Override
    public String getContorlInfo() {
        return null;
    }
}
