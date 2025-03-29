package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "risk_zones")
public class RiskZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double riskLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fault_line_id", nullable = false)
    private FaultLine faultLine;

    @Version
    private Long version;
} 