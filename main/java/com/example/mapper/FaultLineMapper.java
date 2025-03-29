package com.example.mapper;

import com.example.dto.fault.FaultLineDto;
import com.example.dto.fault.RiskZoneDto;
import com.example.entity.FaultLine;
import com.example.entity.RiskZone;
import com.example.mapper.base.BaseMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FaultLineMapper extends BaseMapper<FaultLine, FaultLineDto> {
    @Mapping(target = "riskZones", source = "riskZones")
    @Override
    FaultLineDto toDto(FaultLine entity);

    @Mapping(target = "riskZones", ignore = true)
    @Override
    FaultLine toEntity(FaultLineDto dto);

    @AfterMapping
    default void mapRiskZones(@MappingTarget FaultLineDto dto, FaultLine entity) {
        if (entity.getRiskZones() != null) {
            dto.setRiskZones(entity.getRiskZones().stream()
                    .map(this::toRiskZoneDto)
                    .toList());
        }
    }

    @Mapping(target = "faultLineId", source = "faultLine.id")
    RiskZoneDto toRiskZoneDto(RiskZone entity);

    @Mapping(target = "faultLine", ignore = true)
    RiskZone toRiskZoneEntity(RiskZoneDto dto);

    @AfterMapping
    default void mapFaultLine(@MappingTarget RiskZone entity, RiskZoneDto dto, FaultLine faultLine) {
        if (dto.getFaultLineId() != null) {
            entity.setFaultLine(faultLine);
        }
    }
} 