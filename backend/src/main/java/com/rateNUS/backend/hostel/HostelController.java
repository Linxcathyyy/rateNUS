package com.rateNUS.backend.hostel;

import com.rateNUS.backend.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

/**
 * Serves as the API layer for Hostels.
 */
@RestController
@RequestMapping(path = "hostel")
@CrossOrigin(Config.frontendURL)
public class HostelController {
    // Lowest to highest
    private static final Comparator<Hostel> HOSTEL_COMPARATOR_BY_ID = Comparator.comparing(Hostel::getId);
    // Lowest to highest
    private static final Comparator<Hostel> HOSTEL_COMPARATOR_BY_RATING = Comparator.comparing(Hostel::getRating);

    private final HostelService hostelService;

    @Autowired
    public HostelController(HostelService hostelService) {
        this.hostelService = hostelService;
    }

    @GetMapping
    public List<Hostel> getHostels() {
        return hostelService.getHostels(HOSTEL_COMPARATOR_BY_ID);
    }

    @GetMapping(path = "isHighToLow/{isHighToLow}")
    public List<Hostel> getHostels(@PathVariable("isHighToLow") boolean isHighToLow) {
        Comparator<Hostel> hostelComparator = isHighToLow
                ? HOSTEL_COMPARATOR_BY_RATING.reversed()
                : HOSTEL_COMPARATOR_BY_RATING;

        return hostelService.getHostels(hostelComparator);
    }

    @GetMapping(path = "{hostelId}")
    public Hostel getHostel(@PathVariable("hostelId") long hostelId) {
        return hostelService.getHostel(hostelId);
    }

    @PostMapping
    public List<Hostel> findHostel(@RequestBody String keywordJson) {
        return hostelService.findHostel(keywordJson);
    }
}
