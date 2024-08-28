package com.surveydb.UI;

import javax.swing.*;

import com.surveydb.UI.AdminUI;
import com.surveydb.UI.UserUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginIU {
    SwingUtilities.invokeLater(()->
    {
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
            loginFrame.add(new JLabel());
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
                            new AdminUI().showAdminMenu();
                        } else {
                            new UserUI().showUserMenu();
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

    private static boolean authenticate(String username, String password) {
        return "admin".equals(username) && "adminpass".equals(password) ||
                "user".equals(username) && "userpass".equals(password);
    }
}
