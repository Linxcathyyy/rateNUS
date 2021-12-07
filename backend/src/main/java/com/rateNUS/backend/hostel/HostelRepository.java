package com.rateNUS.backend.hostel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides access to database required by {@code HostelService}.
 */
@Repository
public interface HostelRepository extends JpaRepository<Hostel, Long> {

}
