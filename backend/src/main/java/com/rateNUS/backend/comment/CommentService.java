package com.rateNUS.backend.comment;

import com.rateNUS.backend.exception.TypeNotFoundException;
import com.rateNUS.backend.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public Optional<Comment> getComment(long commentId) {
        return commentRepository.findById(commentId);
    }

    public List<Comment> getComments(long targetId, Type type, String orderBy, boolean isAscending) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        return commentRepository.findByTargetIdAndType(targetId, type, Sort.by(direction, orderBy));
    }

    public List<Comment> getCommentsOfUser(long userId, String orderBy, boolean isAscending) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        return commentRepository.findByUserId(userId, Sort.by(direction, orderBy));
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(long commentId, Comment newComment) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new TypeNotFoundException(Type.comment, commentId));

        comment.setRating(newComment.getRating());
        comment.setText(newComment.getText());

        return comment;
    }

    public Comment deleteComment(long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new TypeNotFoundException(Type.comment, commentId));
        commentRepository.deleteById(commentId);
        return comment;
    }
}
