package com.artur.summer.backend.service.impl;

import com.artur.summer.backend.dto.ui.ClientInfoDto;
import com.artur.summer.backend.model.ClientInfo;
import com.artur.summer.backend.security.ClientTokenService;
import com.artur.summer.backend.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientTokenService tokenService;
    private final ModelMapper modelMapper;

    @Override
    public ClientInfoDto getClientInfo(String token) {
        ClientInfo clientInfo = tokenService.getClientInfoByToken(token);
        ClientInfoDto clientInfoDto = modelMapper.map(clientInfo, ClientInfoDto.class);
        return clientInfoDto;
    }
}
