package com.rateNUS.backend.comment;

import com.rateNUS.backend.hostel.HostelService;
import com.rateNUS.backend.stall.StallService;
import com.rateNUS.backend.util.Config;
import com.rateNUS.backend.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    public CommentController(CommentService commentService,
                             HostelService hostelService,
                             StallService stallService) {
        this.commentService = commentService;
        this.hostelService = hostelService;
        this.stallService = stallService;
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

    @PostMapping
    public void addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);

        switch (comment.getType()) {
            case hostel:
                hostelService.addComment(comment.getTargetId(), comment.getRating());
                break;

            case stall:
                stallService.addComment(comment.getTargetId(), comment.getRating());
                break;

            case studyArea:
                // studyAreaService.addComment(comment.getTargetId(), comment.getRating());
                break;
        }
    }
}
