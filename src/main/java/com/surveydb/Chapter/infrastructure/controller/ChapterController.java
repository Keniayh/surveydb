package com.surveydb.Chapter.infrastructure.controller;

import java.sql.Timestamp;
import java.util.Optional;
import javax.swing.JOptionPane;
import com.surveydb.Chapter.application.CreateChapterUseCase;
import com.surveydb.Chapter.application.FindChapterUseCase;
import com.surveydb.Chapter.application.UpdateChapterUseCase;
import com.surveydb.Chapter.domain.entity.Chapter;
import com.surveydb.Chapter.domain.service.ChapterService;
import com.surveydb.Chapter.infrastructure.repository.ChapterRepository;

public class ChapterController {
    private ChapterService chapterService;
    private CreateChapterUseCase createChapterUseCase;
    private FindChapterUseCase findChapterUseCase;
    private UpdateChapterUseCase updateChapterUseCase;

    public ChapterController() {
        this.chapterService = new ChapterRepository();
        this.createChapterUseCase = new CreateChapterUseCase(chapterService);
        this.findChapterUseCase = new FindChapterUseCase(chapterService);
        this.updateChapterUseCase = new UpdateChapterUseCase(chapterService);
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
            String idString = JOptionPane.showInputDialog(null, "Enter chapter ID:");
            if (idString != null && !idString.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idString);
                    
                    findChapterUseCase.execute(id).ifPresentOrElse(
                        chapterFound -> {
                            JOptionPane.showMessageDialog(null,
                                "This is the chapter:\n" + chapterFound.getChapter_title(), // tengo que pedir toda la info!
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
    
    public void updateChapter() {
        try {
            // Solicitar el ID del capítulo a actualizar mediante JOptionPane
            String idStr = JOptionPane.showInputDialog("Enter chapter ID to update:");
    
            // Validar que la entrada para el ID sea un número
            int id = Integer.parseInt(idStr);
    
            // Solicitar nuevas entradas del usuario
            String chapterNumber = JOptionPane.showInputDialog("Enter new chapter number:");
            String chapterTitle = JOptionPane.showInputDialog("Enter new chapter title:");
            String surveyIdStr = JOptionPane.showInputDialog("Enter new survey ID:");
    
            // Validar que la entrada para surveyId sea un número
            int surveyId = Integer.parseInt(surveyIdStr);
    
            // Crear instancia de Chapter y establecer valores
            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setCreated_at(new Timestamp(System.currentTimeMillis()));  // Puedes cambiar esto si necesitas una fecha específica
            chapter.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            chapter.setSurvey_id(surveyId);
            chapter.setChapter_number(chapterNumber);
            chapter.setChapter_title(chapterTitle);
    
            // Ejecutar caso de uso para actualizar el capítulo
            updateChapterUseCase.execute(chapter);
    
            JOptionPane.showMessageDialog(null, "Chapter updated successfully!");
    
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input for chapter ID or survey ID. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

}
