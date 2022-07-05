package com.artur.summer.backend.dto.impl;

import com.artur.summer.backend.dto.TransferDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClientInternalTransferDto extends TransferDto {
    private String toUuid;
}
