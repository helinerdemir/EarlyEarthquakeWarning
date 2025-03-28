package com.earthquake.simulation;

import com.earthquake.model.Earthquake;
import com.earthquake.model.EarthquakeStatus;
import com.earthquake.service.EarthquakeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class StationSimulator {
    
    private final EarthquakeService earthquakeService;
    private final Random random = new Random();
    
    @Scheduled(fixedRate = 60000) // Her dakika çalışır
    public void simulateSeismicActivity() {
        log.info("Simulating seismic activity...");
        
        // Türkiye sınırları içinde rastgele koordinat
        double lat = 36 + (42 - 36) * random.nextDouble();
        double lon = 26 + (45 - 26) * random.nextDouble();
        
        // Rastgele büyüklük (3.0 - 7.0 arası)
        double magnitude = 3.0 + (4.0 * random.nextDouble());
        
        if (magnitude > 4.0) { // Sadece 4+ depremleri kaydet
            try {
                Earthquake earthquake = new Earthquake();
                earthquake.setMagnitude(magnitude);
                earthquake.setLatitude(lat);
                earthquake.setLongitude(lon);
                earthquake.setDepth(10.0 + (random.nextDouble() * 20));
                earthquake.setOccurrenceTime(LocalDateTime.now());
                earthquake.setStatus(EarthquakeStatus.DETECTED);
                earthquake.setLocation(String.format("Lat: %.4f, Lon: %.4f", lat, lon));
                
                earthquakeService.save(earthquake);
                log.info("Simulated earthquake detected: Magnitude {}. Location: {}", magnitude, earthquake.getLocation());
            } catch (Exception e) {
                log.error("Error simulating earthquake: {}", e.getMessage());
            }
        }
    }
} 