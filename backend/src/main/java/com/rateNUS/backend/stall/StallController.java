package com.rateNUS.backend.stall;

import com.rateNUS.backend.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Serves as the API layer for Stalls.
 */
@RestController
@RequestMapping(path = "stall")
@CrossOrigin(Config.frontendURL)
public class StallController {
    private final StallService stallService;

    private final int default_pageNum = 0;
    private final int default_pageSize = 5;

    @Autowired
    public StallController(StallService stallService) {
        this.stallService = stallService;
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
}
