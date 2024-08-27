package com.surveydb.Chapter.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Statement;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.surveydb.Chapter.domain.entity.Chapter;
import com.surveydb.Chapter.domain.service.ChapterService;
import java.util.Optional;


public class ChapterRepository implements ChapterService{
    private Connection connection;

    public ChapterRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void createChapter(Chapter chapter) {
        String sql = "INSERT INTO chapter (created_at, survey_id, update_at, chapter_number, chapter_title) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, chapter.getCreated_at());
            statement.setInt(2, chapter.getSurvey_id());
            statement.setTimestamp(3, chapter.getUpdated_at());
            statement.setString(4, chapter.getChapter_number());
            statement.setString(5, chapter.getChapter_title());
    
            int affectedRows = statement.executeUpdate();
    
            // Verifica si se insertó la fila y recupera la clave generada
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        chapter.setId(generatedKeys.getInt(1));  // Asigna el ID generado al objeto Chapter
                    }
                }
            }
            
            System.out.println("Chapter created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Chapter> findChapterById(int id) {
        String query = "SELECT id, chapter_number, chapter_title, created_at, update_at, survey_id FROM chapter WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Chapter chapter = new Chapter(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("created_at"),
                        resultSet.getInt("survey_id"),
                        resultSet.getTimestamp("update_at"),
                        resultSet.getString("chapter_number"),
                        resultSet.getString("chapter_title")
                    );
                    return Optional.of(chapter);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();  // Retorna un Optional vacío si no se encuentra el capítulo
    }

    @Override
    public void updateChapter(Chapter chapter) {
        String sql = "UPDATE chapter SET created_at = ?, survey_id = ?, update_at = ?, chapter_number = ?, chapter_title = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setTimestamp(1, chapter.getCreated_at());
            statement.setInt(2, chapter.getSurvey_id());
            statement.setTimestamp(3, chapter.getUpdated_at());
            statement.setString(4, chapter.getChapter_number());
            statement.setString(5, chapter.getChapter_title());
            statement.setInt(6, chapter.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Chapter updated successfully!");
            } else {
                System.out.println("No chapter was found with the provided ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteChapterById(int id) {
        String deleteQuestionsSql = "DELETE FROM questions WHERE chapter_id = ?";
        String deleteChapterSql = "DELETE FROM chapter WHERE id = ?";
        
        try (PreparedStatement deleteQuestionsStmt = connection.prepareStatement(deleteQuestionsSql);
                PreparedStatement deleteChapterStmt = connection.prepareStatement(deleteChapterSql)) {
    
            // Eliminar preguntas relacionadas
            deleteQuestionsStmt.setInt(1, id);
            deleteQuestionsStmt.executeUpdate();
            
            // Eliminar capítulo
            deleteChapterStmt.setInt(1, id);
            int affectedRows = deleteChapterStmt.executeUpdate();
    
            if (affectedRows > 0) {
                System.out.println("Chapter and related questions deleted successfully!");
            } else {
                System.out.println("No chapter was found with the provided ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
