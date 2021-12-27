package com.rateNUS.backend.comment;

import com.rateNUS.backend.exception.TypeNotFoundException;
import com.rateNUS.backend.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public List<Comment> getComments() {
        return commentRepository.findAllByOrderByIdAsc();
    }

    public Page<Comment> getComments(long targetId, Type type, String orderBy, boolean isAscending, int pageNum,
                                     int numEntriesPerPage) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(pageNum, numEntriesPerPage, Sort.by(direction, orderBy));
        return commentRepository.findByTargetIdAndType(targetId, type, pageRequest);
    }

    public List<Comment> getCommentsOfUser(long userId, String orderBy, boolean isAscending) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        return commentRepository.findByUserId(userId, Sort.by(direction, orderBy));
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    public void updateCommentRating(long commentId, double rating) {
        commentRepository.findById(commentId)
                .orElseThrow(() -> new TypeNotFoundException(Type.comment, commentId))
                .setRating(rating);
    }

    @Transactional
    public void updateCommentText(long commentId, String text) {
        commentRepository.findById(commentId)
                .orElseThrow(() -> new TypeNotFoundException(Type.comment, commentId))
                .setText(text);
    }

    public Comment deleteComment(long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new TypeNotFoundException(Type.comment, commentId));
        commentRepository.deleteById(commentId);
        return comment;
    }
}
