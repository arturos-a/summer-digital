package com.artur.summer.backend.dto.document;

import com.artur.summer.backend.dto.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InternalClientDocument extends Document {
    private String dstAccountUuid;
    private String srcCardUuid;
    private String dstAccountNumber;
    private String dstCardUuid;
    private String dstBankBic;
    private String dstBankName;
    private String dstCorrAccountNumber;
    private String dstBankKpp;
}
