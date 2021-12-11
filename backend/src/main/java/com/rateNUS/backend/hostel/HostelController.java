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
        return hostelService.getHostels("id", true, jsonInput.get("pageNum"), jsonInput.get("pageSize"));
    }

    @PostMapping(path = "isLowToHigh/{isLowToHigh}")
    public List<Hostel> getHostels(@PathVariable("isLowToHigh") boolean isLowToHigh,
                                   @RequestBody Map<String, Integer> jsonInput) {
        return hostelService.getHostels("rating", isLowToHigh, jsonInput.get("pageNum"), jsonInput.get("pageSize"));
    }

    @GetMapping(path = "{hostelId}")
    public Hostel getHostel(@PathVariable("hostelId") long hostelId) {
        return hostelService.getHostel(hostelId);
    }

    @PostMapping(path = "search")
    public List<Hostel> findHostel(@RequestBody Map<String, String> jsonInput) {
        return hostelService.findHostel(jsonInput.get("keyword"));
    }
}
