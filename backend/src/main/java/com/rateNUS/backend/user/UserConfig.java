package com.rateNUS.backend.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rateNUS.backend.util.DummyData;

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
