package com.artur.summer.backend.service;

import com.artur.summer.backend.dto.ui.ClientInfoDto;

public interface ClientService {
    ClientInfoDto getClientInfo(String token);
}
