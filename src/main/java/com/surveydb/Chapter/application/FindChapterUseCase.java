package com.surveydb.Chapter.application;


import com.surveydb.Chapter.domain.entity.Chapter;
import com.surveydb.Chapter.domain.service.ChapterService;
import java.util.Optional;

public class FindChapterUseCase {
    private final ChapterService chapterService;
    
    public FindChapterUseCase(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public Optional<Chapter> execute(int id) {
        return chapterService.findChapterById(id);
    }
}
