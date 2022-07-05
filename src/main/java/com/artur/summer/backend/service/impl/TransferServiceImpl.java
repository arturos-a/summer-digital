package com.artur.summer.backend.service.impl;

import com.artur.summer.backend.constants.DocumentType;
import com.artur.summer.backend.constants.StatusCode;
import com.artur.summer.backend.dto.Document;
import com.artur.summer.backend.dto.impl.ClientInternalTransferDto;
import com.artur.summer.backend.dto.ui.OperationResult;
import com.artur.summer.backend.factories.DocumentFactory;
import com.artur.summer.backend.model.Card;
import com.artur.summer.backend.model.ClientInfo;
import com.artur.summer.backend.repository.CardsRepository;
import com.artur.summer.backend.repository.DocumentRepository;
import com.artur.summer.backend.security.ClientTokenService;
import com.artur.summer.backend.service.TransferService;
import com.artur.summer.backend.workflow.DocumentProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.artur.summer.backend.constants.DocumentFieldsNames.*;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final DocumentProcessor documentProcessor;
    private final Map<DocumentType, DocumentFactory> factories;
    private final CardsRepository cardsRepository;
    private final DocumentRepository documentRepository;
    private final ClientTokenService tokenService;

    @Override
    public OperationResult performSelfTransfer(ClientInternalTransferDto transferDto, String session) {
        ClientInfo clientInfo = tokenService.getClientInfoByToken(session);
        Card srcCard = cardsRepository.findById(transferDto.getFromUuid()).orElseThrow(() -> new RuntimeException("Некорректный uuid карты источника"));
        Card dstCard = cardsRepository.findById(transferDto.getToUuid()).orElseThrow(() -> new RuntimeException("Некорректный uuid карты получателя"));
        DocumentFactory documentFactory = factories.get(DocumentType.CLIENT_INTERNAL);
        Map<String, String> params = Stream.of(Pair.of(SRC_ACCOUNT_UUID, srcCard.getAccountUuid()),
                Pair.of(DST_ACCOUNT_UUID, dstCard.getAccountUuid()),
                Pair.of(AMOUNT, transferDto.getAmount().toString()),
                Pair.of(CLIENT_UUID, clientInfo.getUuid()),
                Pair.of(SRC_CARD_UUID, srcCard.getUuid()),
                Pair.of(DST_CARD_UUID, dstCard.getUuid())
        ).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
        Document document = documentFactory.getDocument(params);
        com.artur.summer.backend.model.Document savedDocument = documentRepository.save(new com.artur.summer.backend.model.Document(DocumentType.CLIENT_INTERNAL, documentFactory.getJsonNode(document), clientInfo.getUuid()));
        documentProcessor.startProcess(savedDocument);
        return new OperationResult(savedDocument.getUuid(), StatusCode.OK);
    }
}
