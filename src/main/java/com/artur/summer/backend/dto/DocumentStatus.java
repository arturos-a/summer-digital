package com.artur.summer.backend.dto;

import com.artur.summer.backend.constants.StatusCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class DocumentStatus {
    private Document documentFields;
    private StatusCode statusCode;
    private LocalDateTime created;
}
