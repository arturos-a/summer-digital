package com.artur.summer.backend.service;

import com.artur.summer.backend.dto.impl.ClientInternalTransferDto;
import com.artur.summer.backend.dto.ui.OperationResult;

public interface TransferService {
    OperationResult performSelfTransfer(ClientInternalTransferDto transferDto, String session);
}
