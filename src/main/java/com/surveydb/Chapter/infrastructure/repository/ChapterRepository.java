package com.surveydb.Chapter.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.surveydb.Chapter.domain.entity.Chapter;
import com.surveydb.Chapter.domain.service.ChapterService;

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
            System.out.println("Se conectó :p");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void createChapter(Chapter chapter) {
        String sql = "INSERT INTO chapter (created_at, survey_id, update_at, chapter_number, chapter_title) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, chapter.getCreated_at());
            statement.setInt(2, chapter.getSurvey_id());
            statement.setTimestamp(3, chapter.getUpdated_at());
            statement.setString(4, chapter.getChapter_number());
            statement.setString(5, chapter.getChapter_title());

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    chapter.setId(generatedKeys.getInt(1));
                }
            }
            connection.close();
          
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
    }

}
