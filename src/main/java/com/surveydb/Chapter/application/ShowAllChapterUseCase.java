package com.surveydb.Chapter.application;

import java.util.List;

import com.surveydb.Chapter.domain.entity.Chapter;
import com.surveydb.Chapter.domain.service.ChapterService;

public class ShowAllChapterUseCase {
    private final ChapterService chapterService;

    public ShowAllChapterUseCase (ChapterService chapterService) {
        this.chapterService = chapterService;
    }
    public List<Chapter> execute() {
        return chapterService.getAllChapter();
    }
}
