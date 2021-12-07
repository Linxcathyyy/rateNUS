package com.rateNUS.backend.hostel;

import com.rateNUS.backend.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Provides the services required by {@code HostelController}.
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
        Optional<Hostel> hostelOptional = hostelRepository.findById(hostelId);

        if (hostelOptional.isEmpty()) {
            throw new IllegalStateException("Hostel with ID " + hostelId + " does not exists.");
        }

        return hostelOptional.get();
    }


    public List<Hostel> findHostel(String keyword) {
        return getAllHostel()
                .stream()
                .filter(hostel -> hostel.getName().contains(keyword))
                .collect(Collectors.toList());
    }

    public List<Comment> getComments(long hostelId) {
        return List.of(
                new Comment(1, hostelId, 4.5, "nice hostel :)", Comment.Type.hostel),
                new Comment(2, hostelId, 5.0, "close to bus station, buildings are a bit old.", Comment.Type.hostel)
        );
    }
}
