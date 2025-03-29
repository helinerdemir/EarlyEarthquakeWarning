package com.example.dto.fault;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaultLineDto {
    private Long id;

    @NotBlank(message = "Fay hattı adı boş olamaz")
    private String name;

    @NotBlank(message = "Fay hattı açıklaması boş olamaz")
    private String description;

    @NotNull(message = "Fay hattı uzunluğu boş olamaz")
    @Positive(message = "Fay hattı uzunluğu pozitif olmalıdır")
    private Double length;

    private List<RiskZoneDto> riskZones;
} 