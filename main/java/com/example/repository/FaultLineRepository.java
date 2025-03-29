package com.example.repository;

import com.example.entity.FaultLine;
import com.example.repository.base.BaseQueryRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaultLineRepository extends BaseQueryRepository<FaultLine, Long> {
} 