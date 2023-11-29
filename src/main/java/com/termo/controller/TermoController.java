package com.termo.controller;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.stream.IntStream;

public class TermoController {
    private final String correctWord;

    public TermoController(String correctWord) {
        this.correctWord = correctWord;
    }

    public JTextField[] createLetterFields() {
        JTextField[] letterFields = new JTextField[5];

        for (int i = 0; i < 5; i++) {
            letterFields[i] = createTextField();
        }

        return letterFields;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(100, 100));
        textField.setFont(new Font("Arial", Font.BOLD, 60));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str.isEmpty()) return;

                if (getLength() + str.length() <= 1) super.insertString(offset, str, attr);
            }
        });

        return textField;
    }

    public void handleValidation(JTextField[] letterFields, JLabel resultLabel) {
        StringBuilder enteredWord = new StringBuilder();
        IntStream.range(0, letterFields.length)
                .forEach(i -> handleLetterValidation(i, enteredWord, letterFields[i]));

        boolean isCorrect = validateWord(enteredWord.toString());
        resultLabel.setText(isCorrect ? "Parabéns, você ganhou!" : "Tente novamente!");
    }

    private void handleLetterValidation(int index, StringBuilder enteredWord, JTextField letterField) {
        String enteredLetter = letterField.getText();
        enteredWord.append(enteredLetter);

        if (index < correctWord.length() && !enteredLetter.isEmpty()) {
            char correctChar = correctWord.charAt(index);
            char enteredChar = enteredLetter.charAt(0);

            if (correctChar == enteredChar) {
                letterField.setBackground(Color.GREEN);
            } else if (correctWord.indexOf(enteredChar) != -1) {
                letterField.setBackground(Color.YELLOW);
            } else {
                letterField.setBackground(Color.RED);
            }
        }
    }

    private boolean validateWord(String word) {
        return word.equalsIgnoreCase(this.correctWord);
    }
}
