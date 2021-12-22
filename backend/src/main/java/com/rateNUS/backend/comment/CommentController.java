package com.rateNUS.backend.comment;

import com.rateNUS.backend.exception.TypeNotFoundException;
import com.rateNUS.backend.hostel.HostelService;
import com.rateNUS.backend.stall.StallService;
import com.rateNUS.backend.studyarea.StudyAreaService;
import com.rateNUS.backend.util.Config;
import com.rateNUS.backend.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Serves as the API layer for Comments.
 */
@RestController
@RequestMapping(path = "comment")
@CrossOrigin(Config.frontendURL)
public class CommentController {
    private final CommentService commentService;
    private final HostelService hostelService;
    private final StallService stallService;
    private final StudyAreaService studyAreaService;

    @Autowired
    public CommentController(CommentService commentService,
                             HostelService hostelService,
                             StallService stallService,
                             StudyAreaService studyAreaService) {
        this.commentService = commentService;
        this.hostelService = hostelService;
        this.stallService = stallService;
        this.studyAreaService = studyAreaService;
    }

    @PostMapping(path = "{type}/{targetId}")
    public Page<Comment> getComments(@PathVariable("type") Type type,
                                     @PathVariable("targetId") long targetId,
                                     @RequestBody Map<String, Object> jsonInput) {
        String orderBy = (String) jsonInput.getOrDefault("orderBy", "timestamp");
        boolean isLowToHigh = (boolean) jsonInput.getOrDefault("isLowToHigh", false);
        int pageNum = (int) jsonInput.getOrDefault("pageNum", 0);
        int pageSize = (int) jsonInput.getOrDefault("pageSize", 5);

        return commentService.getComments(targetId, type, orderBy, isLowToHigh, pageNum, pageSize);
    }

    @PostMapping(path = "user/{userId}")
    public List<Comment> getCommentsOfUser(@PathVariable("userId") long userId,
                                           @RequestBody Map<String, Object> jsonInput) {
        String orderBy = (String) jsonInput.getOrDefault("orderBy", "timestamp");
        boolean isLowToHigh = (boolean) jsonInput.getOrDefault("isLowToHigh", false);

        return commentService.getCommentsOfUser(userId, orderBy, isLowToHigh);
    }

    @PostMapping
    public void addComment(@RequestBody Comment comment) {
        Comment newComment = commentService.addComment(comment);

        switch (newComment.getType()) {
            case hostel:
                hostelService.addComment(newComment.getTargetId(), newComment.getRating());
                break;

            case stall:
                stallService.addComment(newComment.getTargetId(), newComment.getRating());
                break;

            case studyArea:
                studyAreaService.addComment(newComment.getTargetId(), newComment.getRating());
                break;
        }
    }

    @PutMapping(path = "/{commentId}")
    public void updateComment(@PathVariable("commentId") long commentId, @RequestBody Map<String, Object> jsonInput) {
        if (jsonInput.containsKey("text")) {
            String text = (String) jsonInput.get("text");
            commentService.updateCommentText(commentId, text);
        }

        if (jsonInput.containsKey("rating")) {
            Comment comment = commentService.getComment(commentId)
                    .orElseThrow(() -> new TypeNotFoundException(Type.comment, commentId));

            // The old rating must be recorded before updateCommentRating() is called
            double oldRating = comment.getRating();
            double newRating = (double) jsonInput.get("rating");

            commentService.updateCommentRating(commentId, newRating);

            switch (comment.getType()) {
                case hostel:
                    hostelService.updateComment(comment.getTargetId(), oldRating, newRating);
                    break;

                case stall:
                    stallService.updateComment(comment.getTargetId(), oldRating, newRating);
                    break;

                case studyArea:
                    studyAreaService.updateComment(comment.getTargetId(), oldRating, newRating);
                    break;
            }
        }
    }

    @DeleteMapping(path = "/{commentId}")
    public void deleteComment(@PathVariable("commentId") long commentId) {
        Comment deletedComment = commentService.deleteComment(commentId);

        switch (deletedComment.getType()) {
            case hostel:
                hostelService.deleteComment(deletedComment.getTargetId(), deletedComment.getRating());
                break;

            case stall:
                stallService.deleteComment(deletedComment.getTargetId(), deletedComment.getRating());
                break;

            case studyArea:
                studyAreaService.deleteComment(deletedComment.getTargetId(), deletedComment.getRating());
                break;
        }
    }
}
