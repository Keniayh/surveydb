package com.surveydb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.surveydb.Chapter.infrastructure.controller.ChapterController;

public class Main {

    public static void main(String[] args) {
        // Crear y mostrar la ventana principal
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Survey DB Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new BorderLayout());

            // Crear el panel principal
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 1, 10, 10));

            // Crear botones para el menú principal
            JButton adminButton = new JButton("Admin");
            JButton userButton = new JButton("User");
            JButton exitButton = new JButton("Exit");

            // Añadir botones al panel
            panel.add(adminButton);
            panel.add(userButton);
            panel.add(exitButton);

            // Añadir panel al frame
            frame.add(panel, BorderLayout.CENTER);
            frame.setVisible(true);

            // Configurar acciones para los botones
            adminButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleAdminActions();
                }
            });

            userButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleUserActions();
                }
            });

            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int confirm = JOptionPane.showConfirmDialog(frame, 
                        "Are you sure you want to exit?", 
                        "Exit Confirmation", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE);
                    if (confirm == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            });
        });
    }

    private static void handleAdminActions() {
        // Crear un frame para las acciones del admin
        JFrame adminFrame = new JFrame("Admin Actions");
        adminFrame.setSize(400, 300);
        adminFrame.setLayout(new GridLayout(3, 1, 10, 10));

        // Crear botones para las acciones del admin
        JButton addChapterButton = new JButton("Add Chapter");
        JButton viewReportsButton = new JButton("View Reports");
        JButton backButton = new JButton("Back to Main Menu");

        // Añadir botones al frame
        adminFrame.add(addChapterButton);
        adminFrame.add(viewReportsButton);
        adminFrame.add(backButton);

        // Configurar acciones para los botones
        addChapterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChapterController chapterController = new ChapterController();
                chapterController.addChapter();
            }
        });

        viewReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(adminFrame, 
                    "View Reports functionality is not implemented yet.", 
                    "Not Implemented", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFrame.dispose(); // Cierra el frame actual y regresa al menú principal
            }
        });

        adminFrame.setVisible(true);
    }

    private static void handleUserActions() {
        // Crear un frame para las acciones del usuario
        JFrame userFrame = new JFrame("User Actions");
        userFrame.setSize(400, 300);
        userFrame.setLayout(new GridLayout(3, 1, 10, 10));

        // Crear botones para las acciones del usuario
        JButton takeSurveyButton = new JButton("Take Survey");
        JButton viewProfileButton = new JButton("View Profile");
        JButton backButton = new JButton("Back to Main Menu");

        // Añadir botones al frame
        userFrame.add(takeSurveyButton);
        userFrame.add(viewProfileButton);
        userFrame.add(backButton);

        // Configurar acciones para los botones
        takeSurveyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(userFrame, 
                    "Take Survey functionality is not implemented yet.", 
                    "Not Implemented", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });

        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(userFrame, 
                    "View Profile functionality is not implemented yet.", 
                    "Not Implemented", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userFrame.dispose(); // Cierra el frame actual y regresa al menú principal
            }
        });

        userFrame.setVisible(true);
    }
}
