package com.surveydb.UI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


import com.surveydb.Chapter.infrastructure.controller.ChapterController;

public class AdminUI {
    public void showAdminMenu() {
        SwingUtilities.invokeLater(() -> {
            JFrame adminFrame = new JFrame("Admin Actions");
            adminFrame.setSize(600, 400);
            adminFrame.setLayout(new BorderLayout());
            adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Crear el panel principal
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 1, 10, 10));

            // Crear y añadir el mensaje de bienvenida
            JTextArea welcomeText = new JTextArea(
                "Welcome to the Admin Actions Menu!\n" +
                "Here you can manage the following tables:\n"+
                "Select an option to proceed."
            );
            welcomeText.setEditable(false);
            welcomeText.setMargin(new Insets(10, 10, 10, 10));
            panel.add(welcomeText);

            // Crear botones para las acciones administrativas
            JButton chapterButton = new JButton("Manage Chapter");
            JButton surveyButton = new JButton("Manage Survey");
            JButton catalogsButton = new JButton("Manage Catalogs");
            JButton backButton = new JButton("Back to Main Menu");

            // Añadir botones al panel
            panel.add(chapterButton);
            panel.add(surveyButton);
            panel.add(catalogsButton);
            panel.add(backButton);

            // Añadir panel al frame
            adminFrame.add(panel, BorderLayout.CENTER);
            adminFrame.setVisible(true);

            // Configurar acciones para los botones
            chapterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleChapterActions();
                }
            });
            // Puedes añadir funcionalidades adicionales para otros botones aquí.

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adminFrame.dispose();
                    // Regresar al menú principal si es necesario
                }
            });
        });
    }

    private void handleChapterActions() {
        SwingUtilities.invokeLater(() -> {
            JFrame chapterFrame = new JFrame("Manage Chapter");
            chapterFrame.setSize(400, 300);
            chapterFrame.setLayout(new GridLayout(6, 1, 10, 10));
            chapterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JButton addButton = new JButton("Add Chapter");
            JButton searchButton = new JButton("Search Chapter");
            JButton updateButton = new JButton("Update Chapter");
            JButton deleteButton = new JButton("Delete Chapter");
            JButton viewButton = new JButton("View All Chapters");
            JButton backButton = new JButton("Back to Admin Menu");

            chapterFrame.add(addButton);
            chapterFrame.add(searchButton);
            chapterFrame.add(updateButton);
            chapterFrame.add(deleteButton);
            chapterFrame.add(viewButton);
            chapterFrame.add(backButton);

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ChapterController consoleAdapter = new ChapterController();
                    consoleAdapter.addChapter();
                }
            });

            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ChapterController consoleAdapter = new ChapterController();
                    consoleAdapter.findChapter();
                }
            });

            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ChapterController consoleAdapter = new ChapterController();
                    consoleAdapter.updateChapter();
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ChapterController consoleAdapter = new ChapterController();
                    consoleAdapter.deleteChapter();
                }
            });

            viewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ChapterController consoleAdapter = new ChapterController();
                    consoleAdapter.viewAllChapter();
                }
            });

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chapterFrame.dispose();
                    showAdminMenu(); // Regresar al menú de administración
                }
            });

            chapterFrame.setVisible(true);
        });
    }
}
