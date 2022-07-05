package com.artur.summer.backend.workflow;

import com.artur.summer.backend.exception.ControlException;
import com.artur.summer.backend.model.Document;
import com.artur.summer.backend.repository.DocumentRepository;
import com.artur.summer.backend.service.bscontrol.AccountControl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.artur.summer.backend.constants.BpmnExceptionCodes.SRC_ACCOUNT_ERROR;
import static com.artur.summer.backend.constants.DocumentFieldsNames.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class SrcAccountCheckService implements JavaDelegate {
    private final DocumentRepository documentRepository;
    private final List<AccountControl> accountControls;
    private final ObjectMapper objectMapper;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String uuid = (String) execution.getVariable(DOCUMENT_UUID);
        Document doc = documentRepository.findById(uuid).orElseThrow(() -> new ControlException("Ошибка получения документа"));
        com.artur.summer.backend.dto.Document document = objectMapper.treeToValue(doc.getDocument(), com.artur.summer.backend.dto.Document.class);
        try {

            accountControls.forEach(accountControl -> {
                if (!accountControl.check(document.getSrcAccountUuid(), Stream.of(
                        Pair.of(AMOUNT, document.getAmount().toString()),
                        Pair.of(CLIENT_UUID, document.getClientUuid())).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond))
                )) {
                    log.info("Ошибка контроля счета " + accountControl.getContorlInfo());
                    throw new ControlException(accountControl.getContorlInfo());
                }
            });
        } catch (Exception ex) {
            throw new BpmnError(SRC_ACCOUNT_ERROR);
        }
    }
}
