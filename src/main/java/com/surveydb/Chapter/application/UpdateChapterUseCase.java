package com.surveydb.Chapter.application;

import com.surveydb.Chapter.domain.entity.Chapter;
import com.surveydb.Chapter.domain.service.ChapterService;

public class UpdateChapterUseCase {
    private final ChapterService chapterService;

    public UpdateChapterUseCase(ChapterService chapterService) {
        this.chapterService = chapterService;
    }
    
    public void execute(Chapter chapter) {
        chapterService.updateChapter(chapter);
    }
}
