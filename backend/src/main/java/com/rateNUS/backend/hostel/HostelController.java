package com.rateNUS.backend.hostel;

import com.rateNUS.backend.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

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

    @GetMapping
    public List<Hostel> getAllHostel() {
        return hostelService.getAllHostel();
    }

    @GetMapping(path = "{hostelId}")
    public HashMap<String, Object> getHostel(@PathVariable("hostelId") long hostelId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("hostel", hostelService.getHostel(hostelId));
        hashMap.put("comments", hostelService.getComments(hostelId));
        return hashMap;
    }
}
