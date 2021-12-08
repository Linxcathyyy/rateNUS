package com.rateNUS.backend.hostel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

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

    public List<Hostel> findHostel(String keywordJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map
                    = objectMapper.readValue(keywordJson, new TypeReference<Map<String, Object>>() {
            });
            String keyword = (String) map.get("keyword");
            System.out.printf("Search for %s\n", keyword);
//            return getAllHostel()
//                    .stream()
//                    .filter(hostel -> hostel.getName().contains(keyword))
//                    .collect(Collectors.toList());
            return hostelRepository.findByNameIgnoreCaseContaining(keyword);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Transactional
    public void updateHostel(long hostelId, double rating, boolean hasNewComment) {
        Hostel hostel = hostelRepository.findById(hostelId)
                .orElseThrow(() -> new IllegalStateException("Hostel with ID " + hostelId + " does not exist."));
        if (!hasNewComment) {
            return;
        }
        int currentCommentCount = hostel.getCommentCount();
        if (currentCommentCount == 0) {
            hostel.setRating(rating);
        } else {
            double currentRating = hostel.getRating();
            double updatedRating = (currentCommentCount * currentRating + rating) / (currentCommentCount + 1);
            hostel.setRating(updatedRating);
        }
        hostel.setCommentCount(currentCommentCount + 1);
    }
}
