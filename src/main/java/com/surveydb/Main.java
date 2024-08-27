package com.surveydb;

import javax.swing.*;
import com.surveydb.Chapter.infrastructure.controller.ChapterController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        ChapterController consoleAdapter = new ChapterController();
        consoleAdapter.updateChapter();

        SwingUtilities.invokeLater(() -> {
            // Crear y mostrar la ventana de login
            JFrame loginFrame = new JFrame("Login");
            loginFrame.setSize(400, 200);
            loginFrame.setLayout(new GridLayout(3, 2, 10, 10));
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Crear componentes del panel de login
            JLabel usernameLabel = new JLabel("Username:");
            JLabel passwordLabel = new JLabel("Password:");
            JTextField usernameField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            JButton loginButton = new JButton("Login");

            // Añadir componentes al panel
            loginFrame.add(usernameLabel);
            loginFrame.add(usernameField);
            loginFrame.add(passwordLabel);
            loginFrame.add(passwordField);
            loginFrame.add(new JLabel()); // Espacio vacío
            loginFrame.add(loginButton);

            loginFrame.setVisible(true);

            // Acción para el botón de login
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());

                    if (authenticate(username, password)) {
                        // Login exitoso, mostrar el menú según el rol
                        loginFrame.dispose();
                        showMainMenu(username);
                    } else {
                        JOptionPane.showMessageDialog(loginFrame, 
                            "Invalid username or password.", 
                            "Login Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        });
    }

    private static boolean authenticate(String username, String password) {
        // Lógica de autenticación básica (debería ser reemplazada con lógica real)
        return "admin".equals(username) && "adminpass".equals(password) || 
               "user".equals(username) && "userpass".equals(password);
    }

    private static void showMainMenu(String username) {
        SwingUtilities.invokeLater(() -> {
            JFrame menuFrame = new JFrame("Main Menu");
            menuFrame.setSize(400, 300);
            menuFrame.setLayout(new BorderLayout());
            menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Crear el panel principal
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 1, 10, 10));

            // Crear botones para el menú principal
            JButton adminButton = new JButton("Admin Menu");
            JButton userButton = new JButton("User Menu");
            JButton exitButton = new JButton("Exit");

            // Añadir botones al panel
            panel.add(adminButton);
            panel.add(userButton);
            panel.add(exitButton);

            // Añadir panel al frame
            menuFrame.add(panel, BorderLayout.CENTER);
            menuFrame.setVisible(true);

            // Configurar acciones para los botones
            if ("admin".equals(username)) {
                userButton.setEnabled(false); // Desactivar botón de usuario para admin
            } else {
                adminButton.setEnabled(false); // Desactivar botón de admin para usuario
            }

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
                    int confirm = JOptionPane.showConfirmDialog(menuFrame, 
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
        JFrame adminFrame = new JFrame("Admin Actions");
        adminFrame.setSize(400, 300);
        adminFrame.setLayout(new GridLayout(3, 1, 10, 10));

        JButton addChapterButton = new JButton("Add Chapter");
        JButton viewReportsButton = new JButton("View Reports");
        JButton backButton = new JButton("Back to Main Menu");

        adminFrame.add(addChapterButton);
        adminFrame.add(viewReportsButton);
        adminFrame.add(backButton);

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
                adminFrame.dispose();
                showMainMenu("admin"); // Regresar al menú principal
            }
        });

        adminFrame.setVisible(true);
    }

    private static void handleUserActions() {
        JFrame userFrame = new JFrame("User Actions");
        userFrame.setSize(400, 300);
        userFrame.setLayout(new GridLayout(3, 1, 10, 10));


        JButton takeSurveyButton = new JButton("Take Survey");
        JButton viewProfileButton = new JButton("View Profile");
        JButton backButton = new JButton("Back to Main Menu");


        userFrame.add(takeSurveyButton);
        userFrame.add(viewProfileButton);
        userFrame.add(backButton);


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
                userFrame.dispose();
                showMainMenu("user"); // Regresar al menú principal
            }
        });

        userFrame.setVisible(true);
    }
}
