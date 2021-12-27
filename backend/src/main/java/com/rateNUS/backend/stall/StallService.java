package com.rateNUS.backend.stall;

import com.rateNUS.backend.exception.TypeNotFoundException;
import com.rateNUS.backend.util.Type;
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

    public Stall getStall(long stallId) {
        return stallRepository.findById(stallId)
                .orElseThrow(() -> new TypeNotFoundException(Type.stall, stallId));
    }

    public Page<Stall> getStalls(String orderBy, boolean isAscending, int pageNum, int numEntriesPerPage) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(pageNum, numEntriesPerPage, Sort.by(direction, orderBy));
        return stallRepository.findAll(pageRequest);
    }

    public Page<Stall> findStall(String keyword, int pageNum, int pageSize) {
        List<Stall> stallList = stallRepository.findByNameIgnoreCaseContaining(keyword);
        stallList.sort((s1, s2) -> {
            boolean h1BeginsWithKeyword = s1.getName().startsWith(keyword);
            boolean h2BeginsWithKeyword = s2.getName().startsWith(keyword);

            if (h1BeginsWithKeyword && !h2BeginsWithKeyword) {
                return -1;
            } else if (!h1BeginsWithKeyword && h2BeginsWithKeyword) {
                return 1;
            } else {
                return s1.getName().compareTo(s2.getName());
            }
        });

        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        int start = (int) pageRequest.getOffset();
        int end = Math.min(start + pageSize, stallList.size());
        List<Stall> content = stallList.subList(start, end);

        return new PageImpl<>(content, pageRequest, stallList.size());
    }

    @Transactional
    public void addComment(long stallId, double rating) {
        stallRepository.findById(stallId)
                .orElseThrow(() -> new TypeNotFoundException(Type.stall, stallId))
                .addComment(rating);
    }

    @Transactional
    public void updateComment(long stallId, double oldRating, double newRating) {
        stallRepository.findById(stallId)
                .orElseThrow(() -> new TypeNotFoundException(Type.stall, stallId))
                .updateComment(oldRating, newRating);
    }

    @Transactional
    public void deleteComment(long stallId, double rating) {
        stallRepository.findById(stallId)
                .orElseThrow(() -> new TypeNotFoundException(Type.stall, stallId))
                .deleteComment(rating);
    }

    @Transactional
    public void updateStallName(long stallId, String name) {
        stallRepository.findById(stallId)
                .orElseThrow(() -> new TypeNotFoundException(Type.stall, stallId))
                .setName(name);
    }

    @Transactional
    public void updateStallLocation(long stallId, String location) {
        stallRepository.findById(stallId)
                .orElseThrow(() -> new TypeNotFoundException(Type.stall, stallId))
                .setLocation(location);
    }

    @Transactional
    public void updateStallDescription(long stallId, String description) {
        stallRepository.findById(stallId)
                .orElseThrow(() -> new TypeNotFoundException(Type.stall, stallId))
                .setDescription(description);
    }

    @Transactional
    public void updateStallImageUrl(long stallId, List<String> imageUrl) {
        stallRepository.findById(stallId)
                .orElseThrow(() -> new TypeNotFoundException(Type.stall, stallId))
                .setImageUrl(imageUrl);
    }

    @Transactional
    public void updateStallLowestPrice(long stallId, double lowestPrice) {
        stallRepository.findById(stallId)
                .orElseThrow(() -> new TypeNotFoundException(Type.stall, stallId))
                .setLowestPrice(lowestPrice);
    }

    @Transactional
    public void updateStallHighestPrice(long stallId, double highestPrice) {
        stallRepository.findById(stallId)
                .orElseThrow(() -> new TypeNotFoundException(Type.stall, stallId))
                .setHighestPrice(highestPrice);
    }

    public Stall deleteStall(long stallId) {
        Stall stall = stallRepository.findById(stallId)
                .orElseThrow(() -> new TypeNotFoundException(Type.stall, stallId));
        stallRepository.deleteById(stallId);
        return stall;
    }
}
