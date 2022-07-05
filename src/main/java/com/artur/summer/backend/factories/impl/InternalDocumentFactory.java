package com.artur.summer.backend.factories.impl;

import com.artur.summer.backend.constants.DocumentType;
import com.artur.summer.backend.dto.Document;
import com.artur.summer.backend.dto.document.InternalClientDocument;
import com.artur.summer.backend.factories.DocumentFactory;
import com.artur.summer.backend.model.Account;
import com.artur.summer.backend.repository.AccountsRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

import static com.artur.summer.backend.constants.DocumentFieldsNames.*;
import static com.artur.summer.backend.constants.DocumentType.CLIENT_INTERNAL;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class InternalDocumentFactory implements DocumentFactory {

    private AccountsRepository accountsRepository;
    private ObjectMapper mapper;

    public InternalDocumentFactory(AccountsRepository accountsRepository, ObjectMapper mapper) {
        this.accountsRepository = accountsRepository;
        this.mapper = mapper;
    }

    @Override
    public Document getDocument(Map<String, String> fields) {
        String srcAccountUuid = fields.get(SRC_ACCOUNT_UUID);
        String dstAccountUuid = fields.get(DST_ACCOUNT_UUID);
        Account srcAccount = accountsRepository.findById(srcAccountUuid).orElseThrow(() -> new RuntimeException("Некорректный uuid счета источника"));
        Account dstAccount = accountsRepository.findById(dstAccountUuid).orElseThrow(() -> new RuntimeException("Некорректный uuid счета получателя"));
        InternalClientDocument internalCLientDocument = new InternalClientDocument();
        internalCLientDocument.setSrcAccountUuid(srcAccount.getUuid());
        internalCLientDocument.setSrcAccountNumber(srcAccount.getAccountNumber());
        internalCLientDocument.setSrcBankBic(srcAccount.getBankBic());
        internalCLientDocument.setSrcCorrAccountNumber(srcAccount.getCorrAccountNumber());
        internalCLientDocument.setSrcBankKpp(fields.get(srcAccount.getBankKpp()));
        internalCLientDocument.setAmount(new BigDecimal(fields.get(AMOUNT)));
        internalCLientDocument.setDstAccountUuid(dstAccount.getUuid());
        internalCLientDocument.setDstAccountNumber(dstAccount.getAccountNumber());
        internalCLientDocument.setDstBankBic(dstAccount.getBankBic());
        internalCLientDocument.setDstCorrAccountNumber(dstAccount.getCorrAccountNumber());
        internalCLientDocument.setDstBankKpp(dstAccount.getBankKpp());
        internalCLientDocument.setClientUuid(fields.get(CLIENT_UUID));
        internalCLientDocument.setSrcCardUuid(fields.get(SRC_CARD_UUID));
        internalCLientDocument.setDstCardUuid(fields.get(DST_CARD_UUID));
        return internalCLientDocument;
    }

    @Override
    public DocumentType getDocumentType() {
        return CLIENT_INTERNAL;
    }

    @Override
    public JsonNode getJsonNode(Document document) {
        InternalClientDocument internalClientDocument = (InternalClientDocument) document;
        return mapper.valueToTree(internalClientDocument);
    }
}
