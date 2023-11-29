package com.termo.view;

import com.termo.controller.TermoController;

import javax.swing.*;
import java.awt.*;

public class TermoView {
    private final TermoController controller;

    public TermoView(String correctWord) {
        this.controller = new TermoController(correctWord);
        createUI();
    }

    private void createUI() {
        JFrame frame = new JFrame("Termo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JTextField[] letterFields = controller.createLetterFields();
        JButton validateButton = new JButton("Confirmar");
        JLabel resultLabel = new JLabel("");

        validateButton.addActionListener(e -> controller.handleValidation(letterFields, resultLabel));

        for (int i = 0; i < letterFields.length; i++) {
            panel.add(new JLabel("Letter " + (i + 1) + ":"));
            panel.add(letterFields[i]);
        }

        panel.add(new JLabel("Enter the word (one letter per field):"));
        panel.add(new JLabel(""));
        panel.add(validateButton);
        panel.add(resultLabel);
        panel.add(Box.createVerticalGlue());

        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
    }
}
