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

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("TERMO"));
        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("Foi sorteada uma palavra aleatória de 5 letras, tente acertar!"));

        for (JTextField letterField : letterFields) {
            panel.add(letterField);
        }

        JPanel panel4 = new JPanel();
        panel4.add(new JLabel("Tente acertar a palavra (uma letra por campo): "));

        JPanel panel5 = new JPanel();
        panel5.add(validateButton);
        panel.add(resultLabel);
        panel.add(Box.createVerticalGlue());

        frame.setLocationRelativeTo(null);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel);
        frame.add(panel4);
        frame.add(panel5);
        frame.setVisible(true);
    }
}
