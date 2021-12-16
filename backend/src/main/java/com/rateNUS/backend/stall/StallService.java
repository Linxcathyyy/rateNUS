package com.rateNUS.backend.stall;

import com.rateNUS.backend.exception.StallNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Provides the services required by {@code StallController}.
 */
@Service
public class StallService {
    private final StallRepository stallRepository;

    @Autowired
    public StallService(StallRepository stallRepository) {
        this.stallRepository = stallRepository;
    }

    public Page<Stall> getStalls(String orderBy, boolean isAscending, int pageNum, int numEntriesPerPage) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(pageNum, numEntriesPerPage, Sort.by(direction, orderBy));
        return stallRepository.findAll(pageRequest);
    }

    public Stall getStall(long stallId) {
        return stallRepository.findById(stallId)
                .orElseThrow(() -> new StallNotFoundException(stallId));
    }

    public Page<Stall> findStall(String keyword, int pageNum, int pageSize) {
        List<Stall> stallList = stallRepository.findByNameIgnoreCaseContaining(keyword);
        stallList.sort((h1, h2) -> {
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

        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        int start = (int) pageRequest.getOffset();
        int end = Math.min(start + pageSize, stallList.size());
        List<Stall> content = stallList.subList(start, end);

        return new PageImpl<>(content, pageRequest, stallList.size());
    }

    @Transactional
    public void updateStall(long stallId, int rating, boolean hasNewComment) {
        if (!hasNewComment) {
            return;
        }

        Stall stall = stallRepository.findById(stallId)
                .orElseThrow(() -> new StallNotFoundException(stallId));

        int currentCommentCount = stall.getCommentCount();
        double updatedRating;

        if (currentCommentCount == 0) {
            updatedRating = rating;
        } else {
            double currentRating = stall.getRating();
            updatedRating = (currentCommentCount * currentRating + rating) / (currentCommentCount + 1);
        }

        stall.setRating(updatedRating);
        stall.incCommentCountByOne();
    }
}
