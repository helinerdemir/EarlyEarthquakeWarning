package com.earthquake.controller;

import com.earthquake.model.Earthquake;
import com.earthquake.service.EarthquakeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/earthquakes")
@RequiredArgsConstructor
@Tag(name = "Earthquake Controller", description = "Earthquake management APIs")
public class EarthquakeController {
    
    private final EarthquakeService earthquakeService;
    
    @GetMapping
    @Operation(summary = "Get all earthquakes")
    public List<Earthquake> getAllEarthquakes() {
        return earthquakeService.findAll();
    }
    
    @PostMapping
    @Operation(summary = "Create a new earthquake")
    public Earthquake createEarthquake(@RequestBody Earthquake earthquake) {
        return earthquakeService.save(earthquake);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get earthquake by ID")
    public ResponseEntity<Earthquake> getEarthquakeById(@PathVariable Long id) {
        return earthquakeService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete earthquake by ID")
    public ResponseEntity<Void> deleteEarthquake(@PathVariable Long id) {
        earthquakeService.deleteById(id);
        return ResponseEntity.ok().build();
    }
} 