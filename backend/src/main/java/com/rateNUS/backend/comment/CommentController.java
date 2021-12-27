package com.rateNUS.backend.comment;

import com.rateNUS.backend.exception.TypeNotFoundException;
import com.rateNUS.backend.hostel.HostelService;
import com.rateNUS.backend.security.jwt.JwtUtils;
import com.rateNUS.backend.stall.StallService;
import com.rateNUS.backend.studyarea.StudyAreaService;
import com.rateNUS.backend.user.UserService;
import com.rateNUS.backend.util.Config;
import com.rateNUS.backend.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Autowired
    public CommentController(CommentService commentService,
                             HostelService hostelService,
                             StallService stallService,
                             StudyAreaService studyAreaService, JwtUtils jwtUtils, UserService userService) {
        this.commentService = commentService;
        this.hostelService = hostelService;
        this.stallService = stallService;
        this.studyAreaService = studyAreaService;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @GetMapping
    public List<Comment> getComments() {
        return commentService.getComments();
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
        long id = comment.getTargetId();

        switch (comment.getType()) {
            case hostel:
                comment.setTargetName(hostelService.getHostel(id).getName());
                hostelService.addComment(id, comment.getRating());
                break;

            case stall:
                comment.setTargetName(stallService.getStall(id).getName());
                stallService.addComment(id, comment.getRating());
                break;

            case studyArea:
                comment.setTargetName(studyAreaService.getStudyArea(id).getName());
                studyAreaService.addComment(id, comment.getRating());
                break;
        }

        commentService.addComment(comment);
    }

    @PutMapping(path = "/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable("commentId") long commentId,
                                           @RequestParam(name = "token") String token,
                                           @RequestParam(name = "username") String username,
                                           @RequestBody Map<String, Object> jsonInput) {

        assert (token != null && username != null);
        if (!jwtUtils.tokenBelongsToUser(token, username) || !userService.userHasComment(username, commentId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (jsonInput.containsKey("text")) {
            String text = (String) jsonInput.get("text");
            commentService.updateCommentText(commentId, text);
        }

        if (jsonInput.containsKey("rating")) {
            Comment comment = commentService.getComment(commentId)
                    .orElseThrow(() -> new TypeNotFoundException(Type.comment, commentId));

            // The old rating must be recorded before updateCommentRating() is called
            double oldRating = comment.getRating();
            double newRating = ((Number) jsonInput.get("rating")).doubleValue();

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
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") long commentId,
                                           @RequestParam(name = "token") String token,
                                           @RequestParam(name = "username") String username) {
        assert (token != null && username != null);
        if (!jwtUtils.tokenBelongsToUser(token, username) || !userService.userHasComment(username, commentId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

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
        return ResponseEntity.ok().build();
    }
}
