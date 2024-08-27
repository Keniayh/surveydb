package com.surveydb.Chapter.domain.service;

import java.util.Optional;
import com.surveydb.Chapter.domain.entity.Chapter;

public interface ChapterService {
    void createChapter(Chapter chapter);
    Optional<Chapter> findChapterById(int id);
    void updateChapter(Chapter chapter);
    void deleteChapterById(int id);
}
