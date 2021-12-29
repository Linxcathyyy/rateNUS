package com.rateNUS.backend.studyarea;

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
 * Provides the services required by {@code StudyAreaService}.
 */
@Service
public class StudyAreaService {
    private final StudyAreaRepository studyAreaRepository;

    @Autowired
    public StudyAreaService(StudyAreaRepository studyAreaRepository) {
        this.studyAreaRepository = studyAreaRepository;
    }

    public StudyArea getStudyArea(long studyAreaId) {
        return studyAreaRepository.findById(studyAreaId)
                .orElseThrow(() -> new TypeNotFoundException(Type.studyArea, studyAreaId));
    }

    public Page<StudyArea> getStudyAreas(String orderBy, boolean isAscending, int pageNum, int numEntriesPerPage) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(pageNum, numEntriesPerPage, Sort.by(direction, orderBy));
        return studyAreaRepository.findAll(pageRequest);
    }

    public Page<StudyArea> findStudyArea(String keyword, int pageNum, int pageSize) {
        List<StudyArea> studyAreaList = studyAreaRepository.findByNameIgnoreCaseContaining(keyword);
        studyAreaList.sort((h1, h2) -> {
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
        int end = Math.min(start + pageSize, studyAreaList.size());
        List<StudyArea> content = studyAreaList.subList(start, end);

        return new PageImpl<>(content, pageRequest, studyAreaList.size());
    }

    @Transactional
    public void addComment(long studyAreaId, double rating) {
        studyAreaRepository.findById(studyAreaId)
                .orElseThrow(() -> new TypeNotFoundException(Type.studyArea, studyAreaId))
                .addComment(rating);
    }

    @Transactional
    public void updateComment(long studyAreaId, double oldRating, double newRating) {
        studyAreaRepository.findById(studyAreaId)
                .orElseThrow(() -> new TypeNotFoundException(Type.studyArea, studyAreaId))
                .updateComment(oldRating, newRating);
    }

    @Transactional
    public void deleteComment(long studyAreaId, double rating) {
        studyAreaRepository.findById(studyAreaId)
                .orElseThrow(() -> new TypeNotFoundException(Type.studyArea, studyAreaId))
                .deleteComment(rating);
    }

    public void saveStudyArea(StudyArea studyArea) {
        studyAreaRepository.save(studyArea);
    }

    public StudyArea deleteStudyArea(long studyAreaId) {
        StudyArea studyArea = studyAreaRepository.findById(studyAreaId)
                .orElseThrow(() -> new TypeNotFoundException(Type.studyArea, studyAreaId));
        studyAreaRepository.deleteById(studyAreaId);
        return studyArea;
    }
}
