package com.rateNUS.backend.studyarea;

import com.rateNUS.backend.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Serves as the API layer for StudyAreas.
 */
@RestController
@RequestMapping(path = "studyarea")
@CrossOrigin(Config.frontendURL)
public class StudyAreaController {
    private final StudyAreaService studyAreaService;

    private final int default_pageNum = 0;
    private final int default_pageSize = 5;

    @Autowired
    public StudyAreaController(StudyAreaService studyAreaService) {
        this.studyAreaService = studyAreaService;
    }

    @GetMapping(path = "{studyAreaId}")
    public StudyArea getStudyArea(@PathVariable("studyAreaId") long studyAreaId) {
        return studyAreaService.getStudyArea(studyAreaId);
    }

    @PostMapping
    public Page<StudyArea> getStudyAreas(@RequestBody Map<String, Object> jsonInput) {
        String orderBy = (String) jsonInput.getOrDefault("orderBy", "id");
        boolean isLowToHigh = (boolean) jsonInput.getOrDefault("isLowToHigh", true);
        int pageNum = (int) jsonInput.getOrDefault("pageNum", default_pageNum);
        int pageSize = (int) jsonInput.getOrDefault("pageSize", default_pageSize);

        return studyAreaService.getStudyAreas(orderBy, isLowToHigh, pageNum, pageSize);
    }

    @PostMapping(path = "search")
    public Page<StudyArea> findStudyArea(@RequestBody Map<String, Object> jsonInput) {
        String keyword = (String) jsonInput.getOrDefault("keyword", "");
        int pageNum = (int) jsonInput.getOrDefault("pageNum", default_pageNum);
        int pageSize = (int) jsonInput.getOrDefault("pageSize", default_pageSize);

        return studyAreaService.findStudyArea(keyword, pageNum, pageSize);
    }
}
