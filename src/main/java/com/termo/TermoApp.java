package com.termo;

import com.termo.api.WordsReader;
import com.termo.view.TermoView;

import javax.swing.SwingUtilities;

public class TermoApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TermoView(WordsReader.getRandomFiveLetterWord()));
    }
}
