package com.example.service.base;

import java.util.List;
import java.util.Optional;

public interface BaseService<D, ID> {
    D save(D dto);
    List<D> saveAll(List<D> dtos);
    Optional<D> findById(ID id);
    List<D> findAll();
    void deleteById(ID id);
    void deleteAll(List<ID> ids);
    boolean existsById(ID id);
} 