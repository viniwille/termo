package com.termo;

import java.util.Random;

import com.termo.api.WordsReader;
import com.termo.view.TermoView;

import javax.swing.SwingUtilities;
import java.util.List;

public class TermoApp {

    public static void main(String[] args) {
        List<String> words = WordsReader.ReadStringsFromTextFile();
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        String randomWord = words.get(randomIndex);

        SwingUtilities.invokeLater(() -> new TermoView(randomWord));
    }
}
