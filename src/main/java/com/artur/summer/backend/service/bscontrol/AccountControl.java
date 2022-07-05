package com.artur.summer.backend.service.bscontrol;

import com.artur.summer.backend.dto.api.BsAccount;

import java.util.Map;

public interface AccountControl {
    boolean check(String accountUuid, Map<String, String> checkParams);

    String getContorlInfo();
}
