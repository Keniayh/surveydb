package com.surveydb.UI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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


public class UserUI {
    public void showUserMenu() {
        SwingUtilities.invokeLater(() -> {
            JFrame userFrame = new JFrame("User Actions");
            userFrame.setSize(400, 300);
            userFrame.setLayout(new BorderLayout());
            userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Crear el panel principal
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 1, 10, 10));

            // Crear botones para las acciones de usuario
            JButton takeSurveyButton = new JButton("Take Survey");
            JButton viewResultsButton = new JButton("View Results");
            JButton backButton = new JButton("Back to Main Menu");

            // Añadir botones al panel
            panel.add(takeSurveyButton);
            panel.add(viewResultsButton);
            panel.add(backButton);

            // Añadir panel al frame
            userFrame.add(panel, BorderLayout.CENTER);
            userFrame.setVisible(true);

            // Configurar acciones para los botones
            takeSurveyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Implementar lógica para tomar encuestas
                }
            });

            viewResultsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Implementar lógica para ver resultados
                }
            });

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userFrame.dispose();
                    // Regresar al menú principal si es necesario
                }
            });
        });
    }
}
