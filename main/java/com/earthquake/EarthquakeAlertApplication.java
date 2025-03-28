package com.earthquake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EarthquakeAlertApplication {
    public static void main(String[] args) {
        SpringApplication.run(EarthquakeAlertApplication.class, args);
    }
} 