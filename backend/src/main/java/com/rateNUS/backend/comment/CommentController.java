package com.rateNUS.backend.comment;

import com.rateNUS.backend.hostel.HostelService;
import com.rateNUS.backend.util.Config;
import com.rateNUS.backend.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Serves as the API layer for Comments.
 */
@RestController
@RequestMapping(path = "comment")
@CrossOrigin(Config.frontendURL)
public class CommentController {
    private final CommentService commentService;
    private final HostelService hostelService;

    @Autowired
    public CommentController(CommentService commentService, HostelService hostelService) {
        this.commentService = commentService;
        this.hostelService = hostelService;
    }

    @GetMapping(path = "{type}/{targetId}")
    public List<Comment> getComments(@PathVariable("targetId") long targetId, @PathVariable("type") Type type) {
        return commentService.getComments(targetId, type, "timestamp", false);
    }

    @GetMapping(path = "{type}/{targetId}/{isLowToHigh}")
    public List<Comment> getComments(@PathVariable("targetId") long targetId,
                                     @PathVariable("type") Type type,
                                     @PathVariable("isLowToHigh") boolean isLowToHigh) {
        return commentService.getComments(targetId, type, "rating", isLowToHigh);
    }

    @PostMapping
    public void addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        hostelService.updateHostel(comment.getTargetId(), comment.getRating(), true);
    }
}
