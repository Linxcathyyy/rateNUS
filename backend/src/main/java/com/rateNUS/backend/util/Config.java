package com.rateNUS.backend.util;

import com.rateNUS.backend.comment.Comment;
import com.rateNUS.backend.comment.CommentController;
import com.rateNUS.backend.hostel.HostelRepository;
import com.rateNUS.backend.image.ImageRepository;
import com.rateNUS.backend.stall.StallRepository;
import com.rateNUS.backend.studyarea.StudyAreaRepository;
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
    CommandLineRunner commandLineRunner(HostelRepository hostelRepository,
                                        StallRepository stallRepository,
                                        StudyAreaRepository studyAreaRepository,
                                        UserRepository userRepository,
                                        ImageRepository imageRepository,
                                        CommentController commentController) {
        return args -> {
            imageRepository.saveAll(DummyData.imageList);
            userRepository.saveAll(DummyData.userList);
            hostelRepository.saveAll(DummyData.hostelList);
            stallRepository.saveAll(DummyData.stallList);
            studyAreaRepository.saveAll(DummyData.studyAreaList);



            for (Comment comment : DummyData.commentList) {
                commentController.addComment(comment);
            }
        };
    }
}
