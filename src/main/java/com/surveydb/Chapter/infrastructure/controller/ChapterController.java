package com.surveydb.Chapter.infrastructure.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import com.surveydb.Chapter.application.CreateChapterUseCase;
import com.surveydb.Chapter.application.DeleteChapterUseCase;
import com.surveydb.Chapter.application.FindChapterUseCase;
import com.surveydb.Chapter.application.ShowAllChapterUseCase;
import com.surveydb.Chapter.application.UpdateChapterUseCase;
import com.surveydb.Chapter.domain.entity.Chapter;
import com.surveydb.Chapter.domain.service.ChapterService;
import com.surveydb.Chapter.infrastructure.repository.ChapterRepository;

public class ChapterController {
    private ChapterService chapterService;
    private CreateChapterUseCase createChapterUseCase;
    private FindChapterUseCase findChapterUseCase;
    private UpdateChapterUseCase updateChapterUseCase;
    private DeleteChapterUseCase deleteChapterUseCase;
    private ShowAllChapterUseCase showAllChapterUseCase;

    public ChapterController() {
        this.chapterService = new ChapterRepository();
        this.createChapterUseCase = new CreateChapterUseCase(chapterService);
        this.findChapterUseCase = new FindChapterUseCase(chapterService);
        this.updateChapterUseCase = new UpdateChapterUseCase(chapterService);
        this.deleteChapterUseCase = new DeleteChapterUseCase(chapterService);
        this.showAllChapterUseCase = new ShowAllChapterUseCase(chapterService);
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
                            
                            StringBuilder chapterDetails = new StringBuilder();
                            chapterDetails.append("Chapter ID: ").append(chapterFound.getId()).append("\n");
                            chapterDetails.append("Created At: ").append(chapterFound.getCreated_at()).append("\n");
                            chapterDetails.append("Survey ID: ").append(chapterFound.getSurvey_id()).append("\n");
                            chapterDetails.append("Updated At: ").append(chapterFound.getUpdated_at()).append("\n");
                            chapterDetails.append("Chapter Number: ").append(chapterFound.getChapter_number()).append("\n");
                            chapterDetails.append("Chapter Title: ").append(chapterFound.getChapter_title()).append("\n");
                            
                            JOptionPane.showMessageDialog(null,
                            chapterDetails.toString(),
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
            chapter.setCreated_at(new Timestamp(System.currentTimeMillis()));  
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
    
    public void deleteChapter() {
        try {
            String idStr = JOptionPane.showInputDialog("Enter chapter ID to delete:");

            int id = Integer.parseInt(idStr);

            deleteChapterUseCase.execute(id);
            
            JOptionPane.showMessageDialog(null, "Chapter deleted successfully!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input for chapter ID. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void viewAllChapter() {
        try {
            // Ejecuta el caso de uso para obtener todos los capítulos
            List<Chapter> chapters = showAllChapterUseCase.execute();

            // Verifica si hay capítulos
            if (chapters.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No chapters found.", "No Data", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Construye un StringBuilder para la información de los capítulos
                StringBuilder chaptersInfo = new StringBuilder();
                for (Chapter chapter : chapters) {
                    chaptersInfo.append("ID: ").append(chapter.getId())
                            .append(", Number: ").append(chapter.getChapter_number())
                            .append(", Title: ").append(chapter.getChapter_title())
                            .append(", Survey ID: ").append(chapter.getSurvey_id())
                            .append(", Created At: ").append(chapter.getCreated_at())
                            .append(", Updated At: ").append(chapter.getUpdated_at())
                            .append("\n");
                }
                // Muestra la información de los capítulos en un cuadro de diálogo
                JOptionPane.showMessageDialog(null, chaptersInfo.toString(), "All Chapters", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            // Muestra un mensaje de error si ocurre una excepción
            JOptionPane.showMessageDialog(null, "An error occurred while fetching chapters: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
