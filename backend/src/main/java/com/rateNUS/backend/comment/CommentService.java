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

    public List<Comment> getComments(long targetId, Type type, Comparator<Comment> commentComparator) {
        List<Comment> commentList = commentRepository.findByTargetIdAndType(targetId, type);
        commentList.sort(commentComparator);

        logger.log(Level.INFO, String.format("Found %d comments", commentList.size()));

        return commentList;
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
}
