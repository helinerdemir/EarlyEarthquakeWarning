package com.example.service.impl;

import com.example.dto.fault.FaultLineDto;
import com.example.entity.FaultLine;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.FaultLineMapper;
import com.example.repository.FaultLineRepository;
import com.example.service.FaultLineService;
import com.example.service.base.BaseServiceImpl;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FaultLineServiceImpl extends BaseServiceImpl<FaultLine, FaultLineDto, Long, FaultLineRepository, FaultLineMapper> implements FaultLineService {

    @Override
    @Transactional
    public FaultLineDto save(FaultLineDto dto) {
        FaultLine entity = mapper.toEntity(dto);
        if (dto.getRiskZones() != null) {
            dto.getRiskZones().forEach(riskZoneDto -> {
                var riskZone = mapper.toRiskZoneEntity(riskZoneDto);
                mapper.mapFaultLine(riskZone, riskZoneDto, entity);
                entity.getRiskZones().add(riskZone);
            });
        }
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public FaultLineDto update(Long id, FaultLineDto dto) {
        FaultLine entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fay hattı bulunamadı: " + id));

        mapper.updateEntity(entity, dto);
        if (dto.getRiskZones() != null) {
            entity.getRiskZones().clear();
            dto.getRiskZones().forEach(riskZoneDto -> {
                var riskZone = mapper.toRiskZoneEntity(riskZoneDto);
                mapper.mapFaultLine(riskZone, riskZoneDto, entity);
                entity.getRiskZones().add(riskZone);
            });
        }
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public Page<FaultLineDto> findAll(String search, Pageable pageable) {
        Predicate predicate = createSearchPredicate(search);
        return repository.findAll(predicate, pageable).map(mapper::toDto);
    }

    private Predicate createSearchPredicate(String search) {
        if (search == null || search.trim().isEmpty()) {
            return null;
        }

        StringPath name = QFaultLine.faultLine.name;
        StringPath description = QFaultLine.faultLine.description;

        return name.containsIgnoreCase(search)
                .or(description.containsIgnoreCase(search));
    }
} 