package com.termo;

import com.termo.view.TermoView;

import javax.swing.SwingUtilities;

public class TermoApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TermoView::new);
    }
}
