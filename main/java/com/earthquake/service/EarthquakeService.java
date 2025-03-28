package com.earthquake.service;

import com.earthquake.model.Earthquake;
import com.earthquake.repository.EarthquakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EarthquakeService {
    
    private final EarthquakeRepository earthquakeRepository;
    
    @Transactional(readOnly = true)
    public List<Earthquake> findAll() {
        return earthquakeRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Earthquake> findById(Long id) {
        return earthquakeRepository.findById(id);
    }
    
    @Transactional
    public Earthquake save(Earthquake earthquake) {
        return earthquakeRepository.save(earthquake);
    }
    
    @Transactional
    public void deleteById(Long id) {
        earthquakeRepository.deleteById(id);
    }
} 