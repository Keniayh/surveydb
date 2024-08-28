package com.surveydb.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginIU {

    public void showMenu() {
        SwingUtilities.invokeLater(() -> {
            JFrame loginFrame = new JFrame("Login");
            loginFrame.setSize(400, 200);
            loginFrame.setLayout(new GridLayout(3, 2, 10, 10));
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel usernameLabel = new JLabel("Username:");
            JLabel passwordLabel = new JLabel("Password:");
            JTextField usernameField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            JButton loginButton = new JButton("Login");

            loginFrame.add(usernameLabel);
            loginFrame.add(usernameField);
            loginFrame.add(passwordLabel);
            loginFrame.add(passwordField);
            loginFrame.add(new JLabel()); // Espacio vacío
            loginFrame.add(loginButton);

            loginFrame.setVisible(true);

            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());

                    if (authenticate(username, password)) {
                        loginFrame.dispose();
                        if ("admin".equals(username)) {
                            new AdminUI().showAdminMenu(); // Llamar al menú de administrador
                        } else {
                            new UserUI().showUserMenu(); // Llamar al menú de usuario
                        }
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

    // Método de autenticación
    private static boolean authenticate(String username, String password) {
        return "admin".equals(username) && "adminpass".equals(password) ||
                "user".equals(username) && "userpass".equals(password);
    }
}

