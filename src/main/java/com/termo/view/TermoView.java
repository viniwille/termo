package com.termo.view;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class TermoView {

    private JTextField[] letterFields;
    private JLabel resultLabel;
    private String correctWord;

    public TermoView(String correctWord) {
        this.correctWord = correctWord;
        createUI();
    }

    private void createUI() {
        JFrame frame = new JFrame("Termo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        letterFields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            letterFields[i] = new JTextField();
            letterFields[i].setPreferredSize(new Dimension(100, 100));
            letterFields[i].setFont(new Font("Arial", Font.BOLD, 60));
            letterFields[i].setHorizontalAlignment(JTextField.CENTER);
            letterFields[i].setDocument(new PlainDocument() {
                @Override
                public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                    if (str.isEmpty()) return;

                    if (getLength() + str.length() <= 1) super.insertString(offset, str, attr);
                }
            });

            panel.add(new JLabel("Letter " + (i + 1) + ":"));
            panel.add(letterFields[i]);
        }

        resultLabel = new JLabel("");

        JButton validateButton = new JButton("Confirmar");

        panel.add(new JLabel("Enter the word (one letter per field):"));
        panel.add(new JLabel(""));
        panel.add(validateButton);
        panel.add(resultLabel);

        validateButton.addActionListener(e -> {
            StringBuilder enteredWord = new StringBuilder();
            for (int i = 0; i < letterFields.length; i++) {
                String enteredLetter = letterFields[i].getText();
                enteredWord.append(enteredLetter);

                if (i < correctWord.length() && !enteredLetter.isEmpty()) {
                    char correctChar = correctWord.charAt(i);
                    char enteredChar = enteredLetter.charAt(0);

                    if (correctChar == enteredChar) {
                        letterFields[i].setBackground(Color.GREEN);
                    } else if (correctWord.indexOf(enteredChar) != -1) {
                        letterFields[i].setBackground(Color.YELLOW);
                    } else {
                        letterFields[i].setBackground(Color.RED);
                    }
                }
            }

            boolean isCorrect = validateWord(enteredWord.toString());

            resultLabel.setText(isCorrect ? "Parabéns, você ganhou!" : "Tente novamente!");
        });

        frame.add(Box.createVerticalGlue());
        frame.setLocationRelativeTo(null);
        frame.add(panel);

        frame.setVisible(true);
    }

    private boolean validateWord(String word) {
        return word.equalsIgnoreCase(this.correctWord);
    }
}
