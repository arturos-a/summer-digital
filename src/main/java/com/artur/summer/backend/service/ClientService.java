package com.artur.summer.backend.service;

import com.artur.summer.backend.dto.ui.ClientInfoDto;
import com.artur.summer.backend.model.ClientInfo;

public interface ClientService {
    ClientInfoDto getClientInfo(String token);

    ClientInfo getClientFullInfo(String token);
}
