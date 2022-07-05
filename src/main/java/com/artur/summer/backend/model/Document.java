package com.artur.summer.backend.model;

import com.artur.summer.backend.constants.DocumentType;
import com.artur.summer.backend.constants.StatusCode;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Document {
    @Id
    private String uuid = UUID.randomUUID().toString();
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    @Type(type = "json")
    @Column(name = "doc_data", columnDefinition = "json")
    private JsonNode document;
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime updated;
    @Enumerated(EnumType.STRING)
    private StatusCode status = StatusCode.NEW;
    private String statusDescription;
    private String clientUuid;

    public Document(DocumentType documentType, JsonNode document, String clientUuid) {
        this.documentType = documentType;
        this.document = document;
        this.clientUuid = clientUuid;
    }
}
