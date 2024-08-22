package com.surveydb;

import java.sql.Connection;

import com.surveydb.Chapter.infrastructure.controller.ChapterController;

public class Main {
    
    public static void main(String[] args) {
        ChapterController consoleAdapter = new ChapterController();
        consoleAdapter.addChapter();
        
    }
}