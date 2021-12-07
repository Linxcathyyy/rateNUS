package com.rateNUS.backend.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides the services required by {@code CommentController}.
 */
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments(long targetId) {
        System.out.println(commentRepository.findAllCommentsByTargetId(targetId));

        return commentRepository.findAllCommentsByTargetId(targetId);
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
}
