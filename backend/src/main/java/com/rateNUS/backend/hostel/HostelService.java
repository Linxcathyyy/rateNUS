package com.rateNUS.backend.hostel;

import com.rateNUS.backend.util.DummyData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Provides the services required by the API layer for Hostels.
 */
@Service
public class HostelService {
    public List<Hostel> getAllHostel() {
        return DummyData.hostelList;
    }

    public Hostel getHostel(long hostelId) {
        Optional<Hostel> hostelOptional = getAllHostel().stream()
                .filter(hostel -> hostel.getId() == hostelId)
                .findFirst();

        if (hostelOptional.isEmpty()) {
            System.out.println("Hostel with ID \" + hostelId + \" does not exists.");
            // throw new Exception("Hostel with ID " + hostelId + " does not exists.");
        }

        return hostelOptional.get();
    }
}
