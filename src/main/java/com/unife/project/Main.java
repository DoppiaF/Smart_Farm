package com.unife.project;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Eseguiamo la GUI in un thread separato per garantire thread-safety
        SwingUtilities.invokeLater(() -> {
            // Creiamo la finestra principale
            JFrame frame = new JFrame("My First Swing Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            
            // Creiamo un'etichetta e la aggiungiamo al frame
            JLabel label = new JLabel("Hello, Swing!", SwingConstants.CENTER);
            frame.getContentPane().add(label);

            // Rendi la finestra visibile
            frame.setVisible(true);
        });
    }
}
