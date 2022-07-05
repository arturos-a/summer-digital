package com.artur.summer.backend.factories;

import com.artur.summer.backend.constants.DocumentType;
import com.artur.summer.backend.dto.Document;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public interface DocumentFactory {
    Document getDocument(Map<String, String> fields);

    DocumentType getDocumentType();

    JsonNode getJsonNode(Document document);
}
