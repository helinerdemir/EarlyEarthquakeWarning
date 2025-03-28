package com.earthquake.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "stations")
@Data
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(nullable = false)
    private Double latitude;
    
    @Column(nullable = false)
    private Double longitude;
    
    @Column(nullable = false)
    private Boolean isActive;
    
    @Column(name = "last_maintenance_date")
    private java.time.LocalDate lastMaintenanceDate;
    
    // Getter ve Setter metodları
} 