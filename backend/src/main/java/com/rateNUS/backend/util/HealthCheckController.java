package com.rateNUS.backend.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "healthCheck")
@CrossOrigin(Config.frontendURL)
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok().build();
    }
}
