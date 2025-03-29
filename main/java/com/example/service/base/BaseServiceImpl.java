package com.example.service.base;

import com.example.mapper.base.BaseMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E, D, ID, R extends JpaRepository<E, ID>, M extends BaseMapper<E, D>> implements BaseService<D, ID> {

    protected final R repository;
    protected final M mapper;

    protected BaseServiceImpl(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public D save(D dto) {
        E entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public List<D> saveAll(List<D> dtos) {
        List<E> entities = mapper.toEntityList(dtos);
        entities = repository.saveAll(entities);
        return mapper.toDtoList(entities);
    }

    @Override
    public Optional<D> findById(ID id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<D> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll(List<ID> ids) {
        ids.forEach(this::deleteById);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }
} 