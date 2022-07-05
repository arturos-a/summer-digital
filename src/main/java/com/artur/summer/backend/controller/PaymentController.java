package com.artur.summer.backend.controller;

import com.artur.summer.backend.dto.TransferDto;
import com.artur.summer.backend.dto.impl.ClientInternalTransferDto;
import com.artur.summer.backend.dto.ui.OperationResult;
import com.artur.summer.backend.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.artur.summer.backend.security.AuthFilter.X_AUTHORIZATION;

@RestController
@RequiredArgsConstructor
public class PaymentController extends Api {
    private final TransferService trasnferService;

    @PostMapping(value = "/performTransfer")
    public @ResponseBody
    OperationResult getAccounts(@RequestBody ClientInternalTransferDto transferDto, @RequestHeader(X_AUTHORIZATION) String session) {
        return trasnferService.performSelfTransfer(transferDto, session);
    }
}
