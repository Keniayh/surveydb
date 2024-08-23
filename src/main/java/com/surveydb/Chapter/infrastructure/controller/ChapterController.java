package com.surveydb.Chapter.infrastructure.controller;

import java.sql.Timestamp;
import javax.swing.JOptionPane;
import com.surveydb.Chapter.application.CreateChapterUseCase;
import com.surveydb.Chapter.application.FindChapterUseCase;
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
        try {
            // Solicitar entrada del usuario mediante JOptionPane
            String chapterNumber = JOptionPane.showInputDialog("Enter chapter number:");
            String chapterTitle = JOptionPane.showInputDialog("Enter chapter title:");
            String surveyIdStr = JOptionPane.showInputDialog("Enter survey ID:");

            // Validar que la entrada para surveyId sea un número
            int surveyId = Integer.parseInt(surveyIdStr);

            // Crear instancia de Chapter y establecer valores
            Chapter chapter = new Chapter();
            chapter.setCreated_at(new Timestamp(System.currentTimeMillis()));
            chapter.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            chapter.setSurvey_id(surveyId);
            chapter.setChapter_number(chapterNumber);
            chapter.setChapter_title(chapterTitle);

            // Ejecutar caso de uso para crear el capítulo
            createChapterUseCase.execute(chapter);

            JOptionPane.showMessageDialog(null, "Chapter created successfully!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input for survey ID. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void findChapter() {
        try {
            // Solicitar el ID del capítulo mediante JOptionPane
            String idString = JOptionPane.showInputDialog(null, "Enter chapter ID:");
            
            // Validar la entrada
            if (idString != null && !idString.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idString);
                    
                    FindChapterUseCase.execute(id).ifPresentOrElse(
                        chapterFound -> {
                            JOptionPane.showMessageDialog(null,
                                "This is the chapter:\n" + chapterFound.getChapter_title(),
                                "Chapter Details",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        },
                        () -> JOptionPane.showMessageDialog(null,
                            "Chapter not found.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                        )
                    );
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                        "Invalid ID format. Please enter a numeric value.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(null,
                    "No ID entered.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "An error occurred: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

}
