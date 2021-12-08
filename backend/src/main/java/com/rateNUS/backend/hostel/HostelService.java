package com.rateNUS.backend.hostel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return hostelRepository.findByNameIgnoreCaseContaining(keyword);

//        return getAllHostel()
//                .stream()
//                .filter(hostel -> hostel.getName().contains(keyword))
//                .collect(Collectors.toList());
    }
}
