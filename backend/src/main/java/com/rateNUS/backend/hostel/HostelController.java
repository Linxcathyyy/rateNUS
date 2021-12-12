package com.rateNUS.backend.hostel;

import com.rateNUS.backend.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Serves as the API layer for Hostels.
 */
@RestController
@RequestMapping(path = "hostel")
@CrossOrigin(Config.frontendURL)
public class HostelController {
    private final HostelService hostelService;

    @Autowired
    public HostelController(HostelService hostelService) {
        this.hostelService = hostelService;
    }

    @PostMapping
    public List<Hostel> getHostels(@RequestBody Map<String, Integer> jsonInput) {
        int pageNum = jsonInput.getOrDefault("pageNum", 0);
        int pageSize = jsonInput.getOrDefault("pageSize", 5);
        return hostelService.getHostels("id", true, pageNum, pageSize);
    }

    @PostMapping(path = "isLowToHigh/{isLowToHigh}")
    public List<Hostel> getHostels(@PathVariable("isLowToHigh") boolean isLowToHigh,
                                   @RequestBody Map<String, Integer> jsonInput) {
        int pageNum = jsonInput.getOrDefault("pageNum", 0);
        int pageSize = jsonInput.getOrDefault("pageSize", 5);
        return hostelService.getHostels("rating", isLowToHigh, pageNum, pageSize);
    }

    @GetMapping(path = "{hostelId}")
    public Hostel getHostel(@PathVariable("hostelId") long hostelId) {
        return hostelService.getHostel(hostelId);
    }

    @PostMapping(path = "search")
    public List<Hostel> findHostel(@RequestBody Map<String, String> jsonInput) {
        String keyword = jsonInput.getOrDefault("keyword", "");
        return hostelService.findHostel(keyword);
    }
}
