package com.artur.summer.backend.workflow;

import com.artur.summer.backend.constants.StatusCode;
import com.artur.summer.backend.model.Document;
import com.artur.summer.backend.repository.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.artur.summer.backend.constants.DocumentFieldsNames.DOCUMENT_TYPE;
import static com.artur.summer.backend.constants.DocumentFieldsNames.DOCUMENT_UUID;

@Service
@Slf4j
public class DocumentProcessor {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private DocumentRepository documentRepository;
    private final ConcurrentLinkedQueue<TaskDto> queue = new ConcurrentLinkedQueue<>();

    public void startProcess(Document savedDocument) {
        Map<String, Object> variables = new HashMap<>();
        variables.put(DOCUMENT_UUID, savedDocument.getUuid());
        variables.put(DOCUMENT_TYPE, savedDocument.getDocumentType().toString());
        queue.add(TaskDto.from("transferProcess", variables));
        savedDocument.setStatus(StatusCode.PROC);
        documentRepository.save(savedDocument);
    }

    @Scheduled(fixedRate = 10000)
    @Async("asyncTaskPool")
    public void taskProcessing() {
        TaskDto taskDto = queue.poll();
        if (taskDto == null) {
            return;
        }
        if (taskDto.getParams() != null && !taskDto.getParams().isEmpty()) {
            runtimeService.startProcessInstanceByKey("transferProcess", taskDto.getParams());
        } else {
            throw new RuntimeException("Ошибка обработки задачи");
        }
    }

    static class TaskDto {
        public String getProcessCode() {
            return processCode;
        }

        public Map<String, Object> getParams() {
            return params;
        }

        private final String processCode;
        private final Map<String, Object> params;

        TaskDto(String processCode, Map<String, Object> params) {
            this.processCode = processCode;
            this.params = params;
        }

        static TaskDto from(String processCode, Map<String, Object> params) {
            return new TaskDto(processCode, params);
        }
    }

}
