package com.termo.api;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WordsReader {

    public static List<String> ReadStringsFromTextFile() {
        String url = "https://www.ime.usp.br/~pf/dicios/br-sem-acentos.txt";

        List<String> wordsList = new ArrayList<>();
        int maxLenght = 5;
        try {
            URL urlObject = new URL(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlObject.openStream()));

            String word;
            while ((word = reader.readLine()) != null) {
                if (word.length() == maxLenght) {
                    wordsList.add(word);
                }
            }

            reader.close();

            return wordsList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
