package com.rateNUS.backend.hostel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

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
        return hostelRepository.findById(hostelId)
                .orElseThrow(() -> new IllegalStateException("Hostel with ID " + hostelId + " does not exist."));
    }

    public List<Hostel> findHostel(String keywordJson) {
        Map<String, String> map;
        try {
            map = new ObjectMapper().readValue(keywordJson, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Json Processing Failed");
        }

        String keyword = map.get("keyword");
        return hostelRepository.findByNameIgnoreCaseContaining(keyword);
    }

    @Transactional
    public void updateHostel(long hostelId, double rating, boolean hasNewComment) {
        if (!hasNewComment) {
            return;
        }

        Hostel hostel = hostelRepository.findById(hostelId)
                .orElseThrow(() -> new IllegalStateException("Hostel with ID " + hostelId + " does not exist."));

        int currentCommentCount = hostel.getCommentCount();
        double updatedRating;

        if (currentCommentCount == 0) {
            updatedRating = rating;
        } else {
            double currentRating = hostel.getRating();
            updatedRating = (currentCommentCount * currentRating + rating) / (currentCommentCount + 1);
        }

        hostel.setRating(updatedRating);
        hostel.setCommentCount(currentCommentCount + 1);
    }
}
