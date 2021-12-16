package com.rateNUS.backend.util;

import com.rateNUS.backend.comment.Comment;
import com.rateNUS.backend.comment.CommentController;
import com.rateNUS.backend.hostel.HostelRepository;
import com.rateNUS.backend.user.UserRepository;
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
    CommandLineRunner commandLineRunner(HostelRepository hostelRepository, CommentController commentController,
                                        UserRepository userRepository) {
        return args -> {
            userRepository.saveAll(DummyData.userList);
            hostelRepository.saveAll(DummyData.hostelList);

            for (Comment comment : DummyData.commentList) {
                commentController.addComment(comment);
            }
        };
    }
}
