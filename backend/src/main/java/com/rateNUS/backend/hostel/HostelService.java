package com.rateNUS.backend.hostel;

import com.rateNUS.backend.util.DummyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides the services required by the API layer for Hostels.
 */
@Service
public class HostelService {
    private final HostelRepository hostelRepository;

    @Autowired
    public HostelService(HostelRepository hostelRepository) {
        this.hostelRepository = hostelRepository;
    }

    public List<Hostel> getAllHostel() {
        return hostelRepository.findAll();
    }
}
