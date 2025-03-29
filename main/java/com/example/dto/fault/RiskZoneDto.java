package com.example.dto.fault;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskZoneDto {
    private Long id;

    @NotBlank(message = "Risk bölgesi adı boş olamaz")
    private String name;

    @NotBlank(message = "Risk bölgesi açıklaması boş olamaz")
    private String description;

    @NotNull(message = "Risk seviyesi boş olamaz")
    @Positive(message = "Risk seviyesi pozitif olmalıdır")
    private Double riskLevel;

    private Long faultLineId;
} 