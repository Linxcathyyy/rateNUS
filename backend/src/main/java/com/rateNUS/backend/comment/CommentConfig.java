package com.rateNUS.backend.comment;

import com.rateNUS.backend.util.DummyData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Provides initial sample data to database used by {@code CommentRepository}.
 */
@Configuration
public class CommentConfig {
    @Bean
    CommandLineRunner commentCommandLineRunner(CommentRepository commentRepository) {
        return args -> commentRepository.saveAll(DummyData.commentList);
    }
}
