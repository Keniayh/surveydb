package com.surveydb.Chapter.application;

import com.surveydb.Chapter.domain.entity.Chapter;
import com.surveydb.Chapter.domain.service.ChapterService;

public class CreateChapterUseCase {
    private final ChapterService chapterService;

    public CreateChapterUseCase(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public void execute(Chapter chapter) {
        chapterService.createChapter(chapter);
    }
}
