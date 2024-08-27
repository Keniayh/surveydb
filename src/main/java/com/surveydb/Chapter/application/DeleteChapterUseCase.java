package com.surveydb.Chapter.application;

import com.surveydb.Chapter.domain.service.ChapterService;

public class DeleteChapterUseCase {
    private final ChapterService chapterService;

    public DeleteChapterUseCase(ChapterService chapterService) {
        this.chapterService = chapterService;
    }
    public void execute(int id) {
        chapterService.deleteChapterById(id);
    }
}
