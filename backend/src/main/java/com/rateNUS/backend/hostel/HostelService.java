package com.rateNUS.backend.hostel;

import com.rateNUS.backend.comment.Comment;
import com.rateNUS.backend.util.DummyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Comment<Hostel>> getComments(long hostelId) {
        return List.of(
                new Comment<>(1, hostelId, 4.5, "nice hostel :)"),
                new Comment<>(2, hostelId, 5.0, "close to bus station, buildings are a bit old.")
        );
    }
}
