package com.artur.summer.backend.workflow;

import com.artur.summer.backend.constants.StatusCode;
import com.artur.summer.backend.exception.ControlException;
import com.artur.summer.backend.model.Document;
import com.artur.summer.backend.repository.AccountsRepository;
import com.artur.summer.backend.repository.DocumentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import static com.artur.summer.backend.constants.DocumentFieldsNames.DOCUMENT_UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class CompleteTransfer implements JavaDelegate {
    private final DocumentRepository documentRepository;
    private final AccountsRepository accountsRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String uuid = (String) execution.getVariable(DOCUMENT_UUID);
        Document doc = documentRepository.findById(uuid).orElseThrow(() -> new ControlException("Ошибка получения документа"));
        doc.setStatus(StatusCode.OK);
        documentRepository.save(doc);
        log.info("CompleteTransfer");
    }
}
