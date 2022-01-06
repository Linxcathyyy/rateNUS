package com.rateNUS.backend.user;

import com.rateNUS.backend.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Serves as the API layer for User.
 */
@RestController
@RequestMapping(path = "user")
@CrossOrigin(Config.frontendURL)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "names")
    public Map<Long, String> getUsernames(@RequestBody Map<String, List<Long>> jsonInput) {
        List<Long> userIds = jsonInput.getOrDefault("ids", new ArrayList<>());
        return userService.getUsernames(userIds);
    }
}
