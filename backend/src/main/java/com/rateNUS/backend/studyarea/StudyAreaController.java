package com.rateNUS.backend.studyarea;

import com.rateNUS.backend.auth.MessageResponse;
import com.rateNUS.backend.security.ApplicationUserRole;
import com.rateNUS.backend.security.jwt.JwtUtils;
import com.rateNUS.backend.user.User;
import com.rateNUS.backend.user.UserService;
import com.rateNUS.backend.util.Config;

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
 * Serves as the API layer for StudyAreas.
 */
@RestController
@RequestMapping(path = "studyarea")
@CrossOrigin(Config.frontendURL)
public class StudyAreaController {
    private final StudyAreaService studyAreaService;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    private final int default_pageNum = 0;
    private final int default_pageSize = 5;

    @Autowired
    public StudyAreaController(StudyAreaService studyAreaService, UserService userService, JwtUtils jwtUtils) {
        this.studyAreaService = studyAreaService;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping(path = "{studyAreaId}")
    public StudyArea getStudyArea(@PathVariable("studyAreaId") long studyAreaId) {
        return studyAreaService.getStudyArea(studyAreaId);
    }

    @PostMapping
    public Page<StudyArea> getStudyAreas(@RequestBody Map<String, Object> jsonInput) {
        String orderBy = (String) jsonInput.getOrDefault("orderBy", "id");
        boolean isLowToHigh = (boolean) jsonInput.getOrDefault("isLowToHigh", true);
        int pageNum = (int) jsonInput.getOrDefault("pageNum", default_pageNum);
        int pageSize = (int) jsonInput.getOrDefault("pageSize", default_pageSize);

        return studyAreaService.getStudyAreas(orderBy, isLowToHigh, pageNum, pageSize);
    }

    @PostMapping(path = "search")
    public Page<StudyArea> findStudyArea(@RequestBody Map<String, Object> jsonInput) {
        String keyword = (String) jsonInput.getOrDefault("keyword", "");
        int pageNum = (int) jsonInput.getOrDefault("pageNum", default_pageNum);
        int pageSize = (int) jsonInput.getOrDefault("pageSize", default_pageSize);

        return studyAreaService.findStudyArea(keyword, pageNum, pageSize);
    }

    // admin function
    @PutMapping(path = "update/{studyAreaId}")
    public ResponseEntity<?> updateStudyArea(@PathVariable("studyAreaId") long studyAreaId,
                                          @RequestParam(name = "token") String token,
                                          @RequestParam(name = "username") String username,
                                          @RequestBody Map<String, Object> jsonInput) {
        if (!jwtUtils.tokenBelongsToUser(token, username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userService.findByUsername(username);
        if (user == null || !user.getRoles().contains(ApplicationUserRole.ADMIN)) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse("User doesn't have permission to update study area.")
            );
        }

        StudyArea studyArea;
        try {
            studyArea = studyAreaService.getStudyArea(studyAreaId);
        } catch (TypeNotPresentException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse("Study Area with id: " + studyAreaId + " doesn't exist.")
            );
        }

        try {
            if (jsonInput.containsKey("name")) {
                String name = (String) jsonInput.get("name");
                studyArea.setName(name);
            }
            if (jsonInput.containsKey("location")) {
                String location = (String) jsonInput.get("location");
                studyArea.setLocation(location);
            }
            if (jsonInput.containsKey("imageUrl")) {
                List<String> imageUrl = (List<String>) jsonInput.get("imageUrl");
                studyArea.setImageUrl(imageUrl);
            }
            studyAreaService.saveStudyArea(studyArea);
        } catch (ClassCastException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(e.getMessage())
            );
        }

        return ResponseEntity.ok(new MessageResponse("Study Area updated successfully."));
    }

    // admin function
    @DeleteMapping(path = "delete/{studyAreaId}")
    public ResponseEntity<?> deleteStudyArea(@PathVariable("studyAreaId") long studyAreaId,
                                          @RequestParam(name = "token") String token,
                                          @RequestParam(name = "username") String username) {
        if (!jwtUtils.tokenBelongsToUser(token, username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userService.findByUsername(username);
        if (user == null || !user.getRoles().contains(ApplicationUserRole.ADMIN)) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse("User doesn't have permission to delete Study Area.")
            );
        }

        StudyArea studyArea;
        try {
            studyArea = studyAreaService.deleteStudyArea(studyAreaId);
        } catch (TypeNotPresentException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse("Study Area with id: " + studyAreaId + " doesn't exist.")
            );
        }

        return ResponseEntity.ok(new MessageResponse("Study Area deleted successfully."));
    }
}
