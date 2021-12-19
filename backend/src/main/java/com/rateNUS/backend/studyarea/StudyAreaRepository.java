package com.rateNUS.backend.studyarea;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides access to database required by {@code StudyAreaService}.
 */
@Repository
public interface StudyAreaRepository extends JpaRepository<StudyArea, Long> {
    List<StudyArea> findByNameIgnoreCaseContaining(String keyword);
}
