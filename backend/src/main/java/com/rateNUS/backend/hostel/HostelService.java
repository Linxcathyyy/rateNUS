package com.rateNUS.backend.hostel;

import com.rateNUS.backend.exception.HostelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

/**
 * Provides the services required by {@code HostelController}.
 */
@Service
public class HostelService {
    private final int numEntriesPerPage = 5;
    private final HostelRepository hostelRepository;

    @Autowired
    public HostelService(HostelRepository hostelRepository) {
        this.hostelRepository = hostelRepository;
    }

    public List<Hostel> getHostels(Comparator<Hostel> hostelComparator, int pageNum) {
        List<Hostel> hostelList = hostelRepository.findAll();
        hostelList.sort(hostelComparator);

        int startIndex = (pageNum - 1) * numEntriesPerPage;
        if (startIndex < 0 || startIndex > hostelList.size()) {
            throw new IllegalStateException("Page out of bound");
        }

        int endIndex = Math.min(hostelList.size(), startIndex + numEntriesPerPage);
        
        return hostelList.subList(startIndex, endIndex);
    }

    public Hostel getHostel(long hostelId) {
        return hostelRepository.findById(hostelId)
                .orElseThrow(() -> new HostelNotFoundException(hostelId));
    }

    public List<Hostel> findHostel(String keyword) {
        List<Hostel> hostelList = hostelRepository.findByNameIgnoreCaseContaining(keyword);
        hostelList.sort((h1, h2) -> {
            boolean h1BeginsWithKeyword = h1.getName().startsWith(keyword);
            boolean h2BeginsWithKeyword = h2.getName().startsWith(keyword);

            if (h1BeginsWithKeyword && !h2BeginsWithKeyword) {
                return -1;
            } else if (!h1BeginsWithKeyword && h2BeginsWithKeyword) {
                return 1;
            } else {
                return h1.getName().compareTo(h2.getName());
            }
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
