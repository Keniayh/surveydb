package com.surveydb;

import com.surveydb.Chapter.infrastructure.controller.ChapterController;

public class Main {
    
    public static void main(String[] args) {
        
        System.out.println("Tengo fe");

        ChapterController consoleAdapter = new ChapterController();
        consoleAdapter.addChapter();

    }
}