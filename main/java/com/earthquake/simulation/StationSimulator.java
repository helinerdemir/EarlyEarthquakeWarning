package com.earthquake.simulation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class StationSimulator {
    
    @Autowired
    private EarthquakeService earthquakeService;
    
    @Scheduled(fixedRate = 60000) // Her dakika çalışır
    public void simulateSeismicActivity() {
        Random random = new Random();
        
        // Türkiye sınırları içinde rastgele koordinat
        double lat = 36 + (42 - 36) * random.nextDouble();
        double lon = 26 + (45 - 26) * random.nextDouble();
        
        // Rastgele büyüklük (3.0 - 7.0 arası)
        double magnitude = 3.0 + (4.0 * random.nextDouble());
        
        if (magnitude > 4.0) { // Sadece 4+ depremleri kaydet
            Earthquake earthquake = new Earthquake();
            earthquake.setMagnitude(magnitude);
            earthquake.setLatitude(lat);
            earthquake.setLongitude(lon);
            earthquake.setDepth(10.0 + (random.nextDouble() * 20));
            earthquake.setOccurrenceTime(LocalDateTime.now());
            earthquake.setStatus("DETECTED");
            
            earthquakeService.save(earthquake);
        }
    }
} 