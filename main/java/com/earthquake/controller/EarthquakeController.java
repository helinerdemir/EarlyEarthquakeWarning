package com.earthquake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/earthquakes")
public class EarthquakeController {
    
    @Autowired
    private EarthquakeService earthquakeService;
    
    @GetMapping
    public List<Earthquake> getAllEarthquakes() {
        return earthquakeService.findAll();
    }
    
    @PostMapping
    public Earthquake createEarthquake(@RequestBody Earthquake earthquake) {
        return earthquakeService.save(earthquake);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Earthquake> getEarthquakeById(@PathVariable Long id) {
        return earthquakeService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
} 