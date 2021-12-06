package com.rateNUS.backend.hostel;

import com.rateNUS.backend.util.DummyData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides the services required by the API layer for Hostels.
 */
@Service
public class HostelService {
    public List<Hostel> getAllHostel() {
        return DummyData.hostelList;
    }
}
