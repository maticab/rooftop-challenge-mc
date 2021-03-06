package com.rooftopmc.challenge.repository;

import com.rooftopmc.challenge.model.Textanalizer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TextanalizerRepository extends PagingAndSortingRepository<Textanalizer, String> {

    @Query(value = "SELECT * FROM textanalizer WHERE chars = ?1",
            countQuery = "SELECT count(*) FROM textanalizer WHERE chars = ?1",
            nativeQuery = true)
    Page<Textanalizer> findAllPageable(int chars, Pageable pageable);

    @Query(value = "SELECT * FROM textanalizer WHERE hash = ?1",
            nativeQuery = true)
    Optional<Textanalizer> findByHash(String hash);
}
