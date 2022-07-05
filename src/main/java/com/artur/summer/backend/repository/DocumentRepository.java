package com.artur.summer.backend.repository;

import com.artur.summer.backend.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, String> {
    List<Document> findAllByClientUuid(String clientUuid);
}
