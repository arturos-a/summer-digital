package com.artur.summer.backend.dto.ui;

import com.artur.summer.backend.constants.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationResult {
    private String docUuid;
    private StatusCode statusCode;
    private String statusText;

    public OperationResult(String docUuid, StatusCode statusCode) {
        this.docUuid = docUuid;
        this.statusCode = statusCode;
    }
}
