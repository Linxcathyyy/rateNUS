package com.rateNUS.backend.stall;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Provides access to database required by {@code StallService}.
 */
@Repository
public interface StallRepository extends JpaRepository<Stall, Long> {
    List<Stall> findByNameIgnoreCaseContaining(String keyword);
}
