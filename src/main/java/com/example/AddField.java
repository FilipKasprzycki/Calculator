package com.example;

import com.example.listeners.ColorfulMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/*
 * Klasa tworzy przyciski i pola dla klasy Window, oraz oblicza ich położenie na podstawie rozmiarów okna
 */

public class AddField {

    // Odległość elementu od krawędzi okna
    private int marginLeftRight;
    private int marginTopBottom;
    // Wysokość i szerokość okna
    private int windowWidth;
    private int windowHeight;
    // Wysokość i szerokość elementów
    private int elementWidth;
    private int elementHeight;
    // Odległość między elementami
    private int spaceHeight;
    // Numer linii
    private int numberOfLines;
    private int number;
    // Czcionka
    private Font font;
    //Listener
    private MouseListener mouseListener;


    AddField(int windowWidth, int windowHeight, int numberOfLines, int marginLeftRight, int marginTopBotton, int spaceHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.numberOfLines = numberOfLines;
        this.marginLeftRight = marginLeftRight; // Musi być podane w procentach
        this.marginTopBottom = marginTopBotton; // Musi być podane w procentach
        this.spaceHeight = spaceHeight; // Musi być podane w procentach
        countParameters();

        mouseListener = new ColorfulMouseListener();
    }


    private void countParameters() {
        marginLeftRight *= windowWidth / 100;
        marginTopBottom *= windowHeight / 100;
        spaceHeight *= windowHeight / 100;
        elementWidth = windowWidth - (2 * marginLeftRight);
        elementHeight = ((windowHeight - (2 * marginTopBottom) - (spaceHeight * (numberOfLines - 1))) / numberOfLines);
        font = new Font(Font.SANS_SERIF, Font.PLAIN, (int) (elementHeight * .5));
    }

    JLabel createJLabel(String text, int width, int x, boolean newLine) {
        JLabel label = new JLabel();

        // Dane pomocnicze
        x += marginLeftRight;
        int y = marginTopBottom + (elementHeight + spaceHeight) * number;
        int height = elementHeight;

        label.setBounds(x, y, width, height);
        label.setText(text);
        label.setFont(font);

        if (newLine) {
            number++;
        }

        return label;
    }

    JButton createJButton(String text) {
        JButton button = new JButton();

        // Dane pomocnicze
        int x = marginLeftRight;
        int y = marginTopBottom + (elementHeight + spaceHeight) * number;
        int width = elementWidth;
        int height = elementHeight;

        button.setBounds(x, y, width, height);
        button.setText(text);
        button.setFont(font);
        button.setBackground(ColorManager.getElementColor());
        button.setBorder(null);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusable(false);
        button.addMouseListener(mouseListener);

        // Przejście do następnej linii
        number++;

        return button;
    }

    JTextField createJTextField() {
        JTextField textField = new JTextField();

        // Dane pomocnicze
        int x = marginLeftRight;
        int y = marginTopBottom + (elementHeight + spaceHeight) * number;
        int width = elementWidth;
        int height = elementHeight;

        textField.setBounds(x, y, width, height);
        textField.setFont(font);
        textField.setBackground(ColorManager.getBackgroundColor());
        textField.setBorder(null);

        // Przejście do następnej linii
        number++;

        return textField;
    }

    JTextField createJTextField(int width, int x, boolean newLine) {
        JTextField textField = new JTextField();

        // Dane pomocnicze
        x += marginLeftRight;
        int y = marginTopBottom + (elementHeight + spaceHeight) * number;
        int height = elementHeight;

        textField.setBounds(x, y, width, height);
        textField.setFont(font);
        textField.setBackground(ColorManager.getElementColor());
        textField.setBorder(null);

        if (newLine) {
            number++;
        }

        return textField;
    }

    JComboBox<String> createJComboBox() {
        JComboBox<String> comboBox = new JComboBox<>();

        // Dane pomocnicze
        int x = marginLeftRight;
        int y = marginTopBottom + (elementHeight + spaceHeight) * number;
        int width = elementWidth;
        int height = elementHeight;

        comboBox.setBounds(x, y, width, height);
        comboBox.setFont(font);
        comboBox.setBackground(ColorManager.getElementColor());
        comboBox.setBorder(null);
        comboBox.setFocusable(false);

        // Przejście do następnej linii
        number++;

        return comboBox;
    }

    int getElementWidth() {
        return elementWidth;
    }
}