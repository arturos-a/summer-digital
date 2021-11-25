package com.artur.summer.backend.repository;

import com.artur.summer.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProductRepository<T extends Product> extends JpaRepository<T, String> {
    @Query("select e from #{#entityName} as e where e.uuid = clientUuid")
    List<T> findAllByClientUuid(String clientUuid);
}
