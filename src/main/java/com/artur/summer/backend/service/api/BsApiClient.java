package com.artur.summer.backend.service.api;

import com.artur.summer.backend.dto.api.BsAccount;

public interface BsApiClient {
    BsAccount getAccountByUuid(String uuid);
}
