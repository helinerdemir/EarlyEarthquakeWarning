package com.example.service;

import com.example.dto.fault.FaultLineDto;
import com.example.service.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FaultLineService extends BaseService<FaultLineDto, Long> {
    Page<FaultLineDto> findAll(String search, Pageable pageable);
} 