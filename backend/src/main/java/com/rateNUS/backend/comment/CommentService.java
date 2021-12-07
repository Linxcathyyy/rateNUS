package com.rateNUS.backend.comment;

import com.rateNUS.backend.util.DummyData;
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

    public List<Comment> getComments(long hostelId) {
        System.out.println(commentRepository.findAllCommentsByTargetId(hostelId));
        
        return DummyData.commentList;
        // return commentRepository.findAllCommentsByTargetId(hostelId);
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
}
