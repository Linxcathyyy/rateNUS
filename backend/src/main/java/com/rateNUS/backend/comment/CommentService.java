package com.rateNUS.backend.comment;

import com.rateNUS.backend.exception.TypeNotFoundException;
import com.rateNUS.backend.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
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

    public Optional<Comment> getComment(long commentId) {
        return commentRepository.findById(commentId);
    }

    public Page<Comment> getComments(long targetId, Type type, String orderBy, boolean isAscending, int pageNum,
                                     int numEntriesPerPage) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(pageNum, numEntriesPerPage, Sort.by(direction, orderBy));
        Page<Comment> page = commentRepository.findByTargetIdAndType(targetId, type, pageRequest);

        logger.log(Level.INFO, String.format("Found %d comments", page.getTotalElements()));

        return page;
    }

    public Page<Comment> getCommentsOfUser(long userId, String orderBy, boolean isAscending, int pageNum,
                                           int numEntriesPerPage) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(pageNum, numEntriesPerPage, Sort.by(direction, orderBy));
        Page<Comment> page = commentRepository.findByUserId(userId, pageRequest);

        logger.log(Level.INFO, String.format("Found %d comments", page.getTotalElements()));

        return page;
    }

    public Comment addComment(Comment comment) {
        CommentService.logger.log(Level.INFO, "Added comment: " + comment);
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
