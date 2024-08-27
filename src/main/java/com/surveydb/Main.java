package com.surveydb;

import javax.swing.*;
import com.surveydb.Chapter.infrastructure.controller.ChapterController;
import com.surveydb.Survey.infrastructure.controller.SurveyController;
// import com.surveydb.Catalogs.infrastructure.controller.CatalogsController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
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
                "Here you can manage the following tables:\n" +
                "1. Chapter\n" +
                "2. Survey\n" +
                "3. Catalogs\n" +
                "4. [Add any other tables here]\n" +
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

            surveyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleSurveyActions();
                }
            });

            catalogsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleCatalogsActions();
                }
            });

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adminFrame.dispose();
                    showMainMenu("admin"); // Regresar al menú principal
                }
            });
        });
    }

    private static void handleChapterActions() {
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
                    ChapterController chapterController = new ChapterController();
                    chapterController.addChapter();
                }
            });

            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ChapterController chapterController = new ChapterController();
                    chapterController.findChapter();
                }
            });

            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ChapterController chapterController = new ChapterController();
                    chapterController.updateChapter();
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ChapterController chapterController = new ChapterController();
                    chapterController.deleteChapter();
                }
            });

            viewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ChapterController chapterController = new ChapterController();
                    chapterController.viewAllChapter();
                }
            });

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chapterFrame.dispose();
                    handleAdminActions(); // Regresar al menú de administración
                }
            });

            chapterFrame.setVisible(true);
        });
    }

    private static void handleSurveyActions() {
        SwingUtilities.invokeLater(() -> {
            JFrame surveyFrame = new JFrame("Manage Survey");
            surveyFrame.setSize(400, 300);
            surveyFrame.setLayout(new GridLayout(6, 1, 10, 10));
            surveyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JButton addButton = new JButton("Add Survey");
            JButton searchButton = new JButton("Search Survey");
            JButton updateButton = new JButton("Update Survey");
            JButton deleteButton = new JButton("Delete Survey");
            JButton viewButton = new JButton("View All Surveys");
            JButton backButton = new JButton("Back to Admin Menu");

            surveyFrame.add(addButton);
            surveyFrame.add(searchButton);
            surveyFrame.add(updateButton);
            surveyFrame.add(deleteButton);
            surveyFrame.add(viewButton);
            surveyFrame.add(backButton);

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // SurveyController surveyController = new SurveyController();
                    // surveyController.addSurvey();
                }
            });

            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // SurveyController surveyController = new SurveyController();
                    // surveyController.searchSurvey();
                }
            });

            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // SurveyController surveyController = new SurveyController();
                    // surveyController.updateSurvey();
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // SurveyController surveyController = new SurveyController();
                    // surveyController.deleteSurvey();
                }
            });

            viewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // SurveyController surveyController = new SurveyController();
                    // surveyController.viewAllSurveys();
                }
            });

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    surveyFrame.dispose();
                    handleAdminActions(); // Regresar al menú de administración
                }
            });

            surveyFrame.setVisible(true);
        });
    }

    private static void handleCatalogsActions() {
        SwingUtilities.invokeLater(() -> {
            JFrame catalogsFrame = new JFrame("Manage Catalogs");
            catalogsFrame.setSize(400, 300);
            catalogsFrame.setLayout(new GridLayout(6, 1, 10, 10));
            catalogsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JButton addButton = new JButton("Add Catalog");
            JButton searchButton = new JButton("Search Catalog");
            JButton updateButton = new JButton("Update Catalog");
            JButton deleteButton = new JButton("Delete Catalog");
            JButton viewButton = new JButton("View All Catalogs");
            JButton backButton = new JButton("Back to Admin Menu");

            catalogsFrame.add(addButton);
            catalogsFrame.add(searchButton);
            catalogsFrame.add(updateButton);
            catalogsFrame.add(deleteButton);
            catalogsFrame.add(viewButton);
            catalogsFrame.add(backButton);

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // CatalogsController catalogsController = new CatalogsController();
                    // catalogsController.addCatalog();
                }
            });

            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // CatalogsController catalogsController = new CatalogsController();
                    // catalogsController.searchCatalog();
                }
            });

            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // CatalogsController catalogsController = new CatalogsController();
                    // catalogsController.updateCatalog();
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // CatalogsController catalogsController = new CatalogsController();
                    // catalogsController.deleteCatalog();
                }
            });

            viewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // CatalogsController catalogsController = new CatalogsController();
                    // catalogsController.viewAllCatalogs();
                }
            });

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    catalogsFrame.dispose();
                    handleAdminActions(); // Regresar al menú de administración
                }
            });

            catalogsFrame.setVisible(true);
        });
    }

    private static void handleUserActions() {
        // Implementar la lógica para las acciones del usuario (no especificado en la pregunta)
    }
}
