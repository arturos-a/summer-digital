package com.artur.summer.backend.service;

import com.artur.summer.backend.constants.DocumentType;
import com.artur.summer.backend.dto.DocumentStatus;
import com.artur.summer.backend.dto.HistoryDto;
import com.artur.summer.backend.model.Document;

import java.util.List;

public interface DocumentService {
    DocumentStatus getDocument(String session, String documentUuid);

    List<HistoryDto> getHistory(String session, String cardUuid);
}
