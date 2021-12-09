package com.rateNUS.backend.comment;

import com.rateNUS.backend.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides the services required by {@code CommentController}.
 */
@Service
public class CommentService {
    private static final Logger logger = Logger.getLogger(CommentService.class.getName());
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments(long targetId, Type type) {
        List<Comment> comments = commentRepository.findByTargetIdAndType(targetId, type);
        comments.sort(Comparator.comparing(Comment::getTimestamp).reversed());

        logger.log(Level.INFO, String.format("Found %d comments", comments.size()));

        return comments;
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
}
