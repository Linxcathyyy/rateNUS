package com.rateNUS.backend.hostel;

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
 * Provides the services required by {@code HostelController}.
 */
@Service
public class HostelService {
    private final HostelRepository hostelRepository;

    @Autowired
    public HostelService(HostelRepository hostelRepository) {
        this.hostelRepository = hostelRepository;
    }

    public Hostel getHostel(long hostelId) {
        return hostelRepository.findById(hostelId)
                .orElseThrow(() -> new TypeNotFoundException(Type.hostel, hostelId));
    }

    public Page<Hostel> getHostels(String orderBy, boolean isAscending, int pageNum, int numEntriesPerPage) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(pageNum, numEntriesPerPage, Sort.by(direction, orderBy));
        return hostelRepository.findAll(pageRequest);
    }

    public Page<Hostel> findHostel(String keyword, int pageNum, int pageSize) {
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

        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        int start = (int) pageRequest.getOffset();
        int end = Math.min(start + pageSize, hostelList.size());
        List<Hostel> content = hostelList.subList(start, end);

        return new PageImpl<>(content, pageRequest, hostelList.size());
    }

    @Transactional
    public void addComment(long hostelId, int rating) {
        hostelRepository.findById(hostelId)
                .orElseThrow(() -> new TypeNotFoundException(Type.hostel, hostelId))
                .addComment(rating);
    }
}
