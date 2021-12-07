package com.rateNUS.backend.hostel;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Serves as the API layer for Hostels.
 */
@RestController
@RequestMapping(path = "hostel")
@CrossOrigin("http://localhost:8081/")
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
    public Hostel getHostel(@PathVariable("hostelId") long hostelId) {
        return hostelService.getHostel(hostelId);
    }
}
