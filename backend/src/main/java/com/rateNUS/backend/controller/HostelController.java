package com.rateNUS.backend.controller;

import com.rateNUS.backend.model.Hostel;
import com.rateNUS.backend.util.DummyData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class serves as the API layer for Hostels.
 */
@RestController
@RequestMapping(path = "hostel")
public class HostelController {
    @GetMapping
    public List<Hostel> getAllHostel() {
        return DummyData.hostelList;
    }
}
