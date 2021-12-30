package com.rateNUS.backend.stall;

import com.rateNUS.backend.auth.MessageResponse;
import com.rateNUS.backend.exception.TypeNotFoundException;
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
 * Serves as the API layer for Stalls.
 */
@RestController
@RequestMapping(path = "stall")
@CrossOrigin(Config.frontendURL)
public class StallController {
    private final StallService stallService;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    private final int default_pageNum = 0;
    private final int default_pageSize = 5;

    @Autowired
    public StallController(StallService stallService, UserService userService, JwtUtils jwtUtils) {
        this.stallService = stallService;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping(path = "{stallId}")
    public Stall getStall(@PathVariable("stallId") long stallId) {
        return stallService.getStall(stallId);
    }

    @PostMapping
    public Page<Stall> getStalls(@RequestBody Map<String, Object> jsonInput) {
        String orderBy = (String) jsonInput.getOrDefault("orderBy", "id");
        boolean isLowToHigh = (boolean) jsonInput.getOrDefault("isLowToHigh", true);
        int pageNum = (int) jsonInput.getOrDefault("pageNum", default_pageNum);
        int pageSize = (int) jsonInput.getOrDefault("pageSize", default_pageSize);

        return stallService.getStalls(orderBy, isLowToHigh, pageNum, pageSize);
    }

    @PostMapping(path = "search")
    public Page<Stall> findStall(@RequestBody Map<String, Object> jsonInput) {
        String keyword = (String) jsonInput.getOrDefault("keyword", "");
        int pageNum = (int) jsonInput.getOrDefault("pageNum", default_pageNum);
        int pageSize = (int) jsonInput.getOrDefault("pageSize", default_pageSize);

        return stallService.findStall(keyword, pageNum, pageSize);
    }

    // ======================================== Admin Functions ========================================

    @PostMapping(path = "new")
    public ResponseEntity<?> addStall(@RequestParam(name = "token") String token,
                                      @RequestParam(name = "username") String username,
                                      @RequestBody Map<String, Object> jsonInput) {
        if (!jwtUtils.tokenBelongsToUser(token, username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userService.findByUsername(username);
        if (user == null || !user.getRoles().contains(ApplicationUserRole.ADMIN)) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse("User doesn't have permission to update stall.")
            );
        }

        StringBuilder stringBuilder = new StringBuilder();
        Stall stall = new Stall();

        try {
            if (jsonInput.containsKey("name")) {
                stall.setName((String) jsonInput.get("name"));
            } else {
                stringBuilder.append("name ");
            }

            if (jsonInput.containsKey("location")) {
                stall.setLocation((String) jsonInput.get("location"));
            } else {
                stringBuilder.append("location ");
            }

            if (jsonInput.containsKey("description")) {
                stall.setDescription((String) jsonInput.get("description"));
            } else {
                stringBuilder.append("description ");
            }

            if (jsonInput.containsKey("imageUrl")) {
                stall.setImageUrl((List<String>) jsonInput.get("imageUrl"));
            } else {
                stringBuilder.append("imageUrl ");
            }

            if (jsonInput.containsKey("lowestPrice")) {
                stall.setLowestPrice(((Number) jsonInput.get("lowestPrice")).doubleValue());
            } else {
                stringBuilder.append("lowestPrice ");
            }

            if (jsonInput.containsKey("highestPrice")) {
                stall.setHighestPrice(((Number) jsonInput.get("highestPrice")).doubleValue());
            } else {
                stringBuilder.append("highestPrice ");
            }
        } catch (ClassCastException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        if (stringBuilder.length() > 0) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse("Missing param(s): " + stringBuilder.toString().trim()));
        } else {
            stallService.saveStall(stall);
            return ResponseEntity.ok(new MessageResponse("Stall added successfully."));
        }
    }

    @PutMapping(path = "update/{stallId}")
    public ResponseEntity<?> updateStall(@PathVariable("stallId") long stallId,
                                         @RequestParam(name = "token") String token,
                                         @RequestParam(name = "username") String username,
                                         @RequestBody Map<String, Object> jsonInput) {
        if (!jwtUtils.tokenBelongsToUser(token, username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userService.findByUsername(username);
        if (user == null || !user.getRoles().contains(ApplicationUserRole.ADMIN)) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse("User doesn't have permission to update stall.")
            );
        }

        Stall stall;
        try {
            stall = stallService.getStall(stallId);
        } catch (TypeNotPresentException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse("Stall with id: " + stallId + " doesn't exist.")
            );
        }

        try {
            if (jsonInput.containsKey("name")) {
                String name = (String) jsonInput.get("name");
                stall.setName(name);
            }
            if (jsonInput.containsKey("location")) {
                String location = (String) jsonInput.get("location");
                stall.setLocation(location);
            }
            if (jsonInput.containsKey("description")) {
                String description = (String) jsonInput.get("description");
                stall.setDescription(description);
            }
            if (jsonInput.containsKey("imageUrl")) {
                List<String> imageUrl = (List<String>) jsonInput.get("imageUrl");
                stall.setImageUrl(imageUrl);
            }
            if (jsonInput.containsKey("lowestPrice")) {
                double lowestPrice = ((Number) jsonInput.get("lowestPrice")).doubleValue();
                stall.setLowestPrice(lowestPrice);
            }
            if (jsonInput.containsKey("highestPrice")) {
                double highestPrice = ((Number) jsonInput.get("highestPrice")).doubleValue();
                stall.setHighestPrice(highestPrice);
            }
            stallService.saveStall(stall);
        } catch (ClassCastException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(e.getMessage())
            );
        }

        return ResponseEntity.ok(new MessageResponse("Stall updated successfully."));
    }

    @DeleteMapping(path = "delete/{stallId}")
    public ResponseEntity<?> deleteStall(@PathVariable("stallId") long stallId,
                                         @RequestParam(name = "token") String token,
                                         @RequestParam(name = "username") String username) {
        if (!jwtUtils.tokenBelongsToUser(token, username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userService.findByUsername(username);
        if (user == null || !user.getRoles().contains(ApplicationUserRole.ADMIN)) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse("User doesn't have permission to delete stall.")
            );
        }

        Stall stall;
        try {
            stall = stallService.deleteStall(stallId);
        } catch (TypeNotFoundException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse("Stall with id: " + stallId + " doesn't exist.")
            );
        }

        return ResponseEntity.ok(new MessageResponse("Stall deleted successfully."));
    }
}
