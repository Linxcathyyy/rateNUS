package com.rateNUS.backend.hostel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rateNUS.backend.exception.HostelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
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
        List<Hostel> hostelList = hostelRepository.findAll();
        hostelList.sort(Comparator.comparing(Hostel::getId));

        return hostelList;
    }

    public Hostel getHostel(long hostelId) {
        return hostelRepository.findById(hostelId)
                .orElseThrow(() -> new HostelNotFoundException(hostelId));
    }

    public List<Hostel> findHostel(String keywordJson) {
        Map<String, String> map;
        try {
            map = new ObjectMapper().readValue(keywordJson, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Json Processing Failed");
        }

        String keyword = map.get("keyword");
        List<Hostel> hostelList = hostelRepository.findByNameIgnoreCaseContaining(keyword);
        hostelList.sort((h1, h2) -> {
            boolean h1BeginsWithKeyword = h1.getName().startsWith(keyword);
            boolean h2BeginsWithKeyword = h2.getName().startsWith(keyword);

            if (h1BeginsWithKeyword && !h2BeginsWithKeyword) return -1;
            if (!h1BeginsWithKeyword && h2BeginsWithKeyword) return 1;
            return h1.getName().compareTo(h2.getName());
        });

        return hostelList;
    }

    @Transactional
    public void updateHostel(long hostelId, int rating, boolean hasNewComment) {
        if (!hasNewComment) {
            return;
        }

        Hostel hostel = hostelRepository.findById(hostelId)
                .orElseThrow(() -> new HostelNotFoundException(hostelId));

        int currentCommentCount = hostel.getCommentCount();
        double updatedRating;

        if (currentCommentCount == 0) {
            updatedRating = rating;
        } else {
            double currentRating = hostel.getRating();
            updatedRating = (currentCommentCount * currentRating + rating) / (currentCommentCount + 1);
        }

        hostel.setRating(updatedRating);
        hostel.incCommentCountByOne();
    }
}
