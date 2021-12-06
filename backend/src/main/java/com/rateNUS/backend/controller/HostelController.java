package com.rateNUS.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rateNUS.backend.model.Hostel;
import com.rateNUS.backend.util.DummyData;

/**
 * This class serves as the API layer for Hostels.
 */
@RestController
@RequestMapping(path = "hostel")
@CrossOrigin("http://localhost:8081/")
public class HostelController {

    @GetMapping
    public List<Hostel> getAllHostel() {
        return DummyData.hostelList;
    }

}