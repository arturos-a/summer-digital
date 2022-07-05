package com.artur.summer.backend.service.impl;

import com.artur.summer.backend.dto.Document;
import com.artur.summer.backend.dto.DocumentStatus;
import com.artur.summer.backend.dto.HistoryDto;
import com.artur.summer.backend.model.ClientInfo;
import com.artur.summer.backend.repository.DocumentRepository;
import com.artur.summer.backend.service.ClientService;
import com.artur.summer.backend.service.DocumentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final ClientService clientService;
    private final ObjectMapper mapper;

    @SneakyThrows
    public DocumentStatus getDocument(String session, String documentUuid) {
        ClientInfo clientInfo = clientService.getClientFullInfo(session);
        com.artur.summer.backend.model.Document document = documentRepository.findById(documentUuid).orElseThrow(() -> new RuntimeException("Документ не найден " + documentUuid));
        if (!document.getClientUuid().equalsIgnoreCase(clientInfo.getUuid())) {
            throw new RuntimeException("Не найден документ для указанного клиента");
        }
        return new DocumentStatus(mapper.treeToValue(document.getDocument(), Document.class), document.getStatus(), document.getCreated());
    }

    @Override
    public List<HistoryDto> getHistory(String session, String cardUuid) {
        ClientInfo clientInfo = clientService.getClientFullInfo(session);
        List<com.artur.summer.backend.model.Document> documents = documentRepository.findAllByClientUuid(clientInfo.getUuid());
        return documents.stream().map(i -> {
            HistoryDto historyDto = null;
            try {
                historyDto = new HistoryDto();
                Document document = mapper.treeToValue(i.getDocument(), Document.class);
                historyDto.setUuid(i.getUuid());
                historyDto.setAmount(document.getAmount());
                historyDto.setDocumentTitle(i.getDocumentType().getName());
                historyDto.setStatus(i.getStatus());
            } catch (JsonProcessingException e) {
                log.error("", e);
            }
            return historyDto;
        }).collect(Collectors.toList());
    }
}
