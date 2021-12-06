package com.rateNUS.backend.hostel;

import com.rateNUS.backend.hostel.HostelRepository;
import com.rateNUS.backend.util.DummyData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HostelConfig {
    //inject sample data upon starting the application
    @Bean
    CommandLineRunner commandLineRunner(HostelRepository hostelRepository) {
      return args -> {
          hostelRepository.saveAll(DummyData.hostelList);
      };
    }
}
