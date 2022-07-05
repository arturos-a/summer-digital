package com.artur.summer.backend.workflow;

import com.artur.summer.backend.constants.StatusCode;
import com.artur.summer.backend.model.Document;
import com.artur.summer.backend.repository.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import static com.artur.summer.backend.constants.DocumentFieldsNames.DOCUMENT_UUID;
import static com.artur.summer.backend.constants.DocumentFieldsNames.ERR_MESSAGE;

@Service
@Slf4j
public class StatusError implements JavaDelegate {
    private DocumentRepository documentRepository;

    public StatusError(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String uuid = (String) execution.getVariable(DOCUMENT_UUID);
        Document document = documentRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Ошибка получения документа"));
        document.setStatus(StatusCode.FAIL);
        document.setStatusDescription((String) execution.getVariable(ERR_MESSAGE));
        documentRepository.save(document);
    }
}
