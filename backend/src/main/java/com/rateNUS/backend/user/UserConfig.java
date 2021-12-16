package com.rateNUS.backend.user;

import com.rateNUS.backend.util.DummyData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Provides initial sample data to database used by {@code UserRepository}.
 */
@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository userRepository) {
        return args -> userRepository.saveAll(DummyData.userList);
    }
}
