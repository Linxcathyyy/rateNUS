package com.rateNUS.backend.hostel;

import com.rateNUS.backend.util.DummyData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Provides initial sample data to database used by {@code HostelRepository}.
 */
@Configuration
public class HostelConfig {
    @Bean
    CommandLineRunner commandLineRunner(HostelRepository hostelRepository) {
        return args -> hostelRepository.saveAll(DummyData.hostelList);
    }
}
