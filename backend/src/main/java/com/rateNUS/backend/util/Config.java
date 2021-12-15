package com.rateNUS.backend.util;

import com.rateNUS.backend.comment.CommentRepository;
import com.rateNUS.backend.hostel.HostelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Provides initial sample data to database used by repositories.
 */
@Configuration
public class Config {
    public static final String frontendURL = "http://localhost:8081/";

    @Bean
    CommandLineRunner commandLineRunner(HostelRepository hostelRepository, CommentRepository commentRepository) {
        return args -> {
            hostelRepository.saveAll(DummyData.hostelList);
            commentRepository.saveAll(DummyData.commentList);
        };
    }
}
