package com.surveydb.Chapter.infrastructure.controller;

import java.sql.Timestamp;
import java.util.Scanner;


import com.surveydb.Chapter.application.CreateChapterUseCase;
import com.surveydb.Chapter.domain.entity.Chapter;
import com.surveydb.Chapter.domain.service.ChapterService;
import com.surveydb.Chapter.infrastructure.repository.ChapterRepository;

public class ChapterController {
    private ChapterService chapterService;
    private CreateChapterUseCase createChapterUseCase;

    public ChapterController() {
        this.chapterService = new ChapterRepository();
        this.createChapterUseCase = new CreateChapterUseCase(chapterService);
    }

    public void addChapter() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the Enter chapter number: ");
            String chapterNumber = scanner.nextLine();

            System.out.println("Enter chapter title: ");
            String chapterTitle = scanner.nextLine();

            System.out.println("Enter survey ID: ");
            int surveyId = Integer.parseInt(scanner.nextLine());

            // crea instacia chapter y establece valores
            
            Chapter chapter = new Chapter();
            chapter.setCreated_at(new Timestamp(System.currentTimeMillis()));
            chapter.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            chapter.setSurvey_id(surveyId);
            chapter.setChapter_number(chapterNumber);
            chapter.setChapter_title(chapterTitle);

            createChapterUseCase.execute(chapter);

            
        }

        System.out.println("Chapter created successfully!");
    }
    
}
