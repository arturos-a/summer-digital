package com.artur.summer.backend.workflow;

import com.artur.summer.backend.constants.DocumentType;
import com.artur.summer.backend.dto.document.InternalClientDocument;
import com.artur.summer.backend.model.Account;
import com.artur.summer.backend.model.Card;
import com.artur.summer.backend.model.Document;
import com.artur.summer.backend.repository.AccountsRepository;
import com.artur.summer.backend.repository.CardsRepository;
import com.artur.summer.backend.repository.DocumentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.artur.summer.backend.constants.DocumentFieldsNames.DOCUMENT_UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransferWorkflowService implements JavaDelegate {
    private final DocumentRepository documentRepository;
    private final AccountsRepository accountsRepository;
    private final CardsRepository cardsRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String docUuid = (String) execution.getVariable(DOCUMENT_UUID);
        BigDecimal transferAmount = null;
        Document document = documentRepository.findById(docUuid).orElseThrow(() -> new RuntimeException("Не найден документ " + docUuid));
        if (document.getDocumentType().equals(DocumentType.CLIENT_INTERNAL)) {
            InternalClientDocument internalClientDocument = objectMapper.treeToValue(document.getDocument(), InternalClientDocument.class);
            transferAmount = internalClientDocument.getAmount();
            String srcAccountUuid = internalClientDocument.getSrcAccountUuid();
            String dstAccountUuid = internalClientDocument.getDstAccountUuid();
            Account accountSrc = accountsRepository.findById(srcAccountUuid).orElseThrow(() -> new RuntimeException("Не найден счет"));
            Account accountDst = accountsRepository.findById(dstAccountUuid).orElseThrow(() -> new RuntimeException("Не найден счет"));
            if (accountSrc.getAmount().compareTo(internalClientDocument.getAmount()) > 0) {
                BigDecimal amount = accountSrc.getAmount();
                log.info("info = " + transferAmount);
                log.info("amount = " + amount);
                accountSrc.setAmount(amount.add(transferAmount.multiply(new BigDecimal("-1"))));
                accountDst.setAmount(amount.add(transferAmount));
                accountsRepository.save(accountSrc);
                accountsRepository.save(accountDst);
                Card srcCard = cardsRepository.findById(internalClientDocument.getSrcCardUuid()).orElseThrow(() -> new RuntimeException("Не найдена карта"));
                Card dstCard = cardsRepository.findById(internalClientDocument.getDstCardUuid()).orElseThrow(() -> new RuntimeException("Не найдена карта"));
                srcCard.setAmount(accountSrc.getAmount());
                dstCard.setAmount(accountDst.getAmount());
                cardsRepository.save(srcCard);
                cardsRepository.save(dstCard);
            }

        }
        execution.setVariable("AmountThreshold", transferAmount.compareTo(BigDecimal.valueOf(5000)));
        log.info("transferWrkflwCompleted");
    }
}
