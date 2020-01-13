package com.example;

import com.example.listeners.ColorfulMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

class FieldManager {

    private static final int NUMBER_OF_LINES = 4;

    private int marginLeftRight;
    private int marginTopBottom;
    private int windowWidth;
    private int windowHeight;
    private int elementWidth;
    private int elementHeight;
    private int spaceHeightInPercent;
    private int lineNumber;
    private Font font;
    private MouseListener mouseListener;


    FieldManager(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.marginLeftRight = 5;
        this.marginTopBottom = 5;
        this.spaceHeightInPercent = 7;
        countParameters();

        mouseListener = new ColorfulMouseListener();
    }


    private void countParameters() {
        marginLeftRight *= windowWidth / 100;
        marginTopBottom *= windowHeight / 100;
        spaceHeightInPercent *= windowHeight / 100;
        elementWidth = windowWidth - (2 * marginLeftRight);
        elementHeight = ((windowHeight - (2 * marginTopBottom) - (spaceHeightInPercent * (NUMBER_OF_LINES - 1))) / NUMBER_OF_LINES);
        font = new Font(Font.SANS_SERIF, Font.PLAIN, (int) (elementHeight * .5));
    }

    JLabel createTextLabel(String text, int width, int x, boolean newLine) {
        JLabel label = new JLabel();

        x += marginLeftRight;
        int y = marginTopBottom + (elementHeight + spaceHeightInPercent) * lineNumber;
        int height = elementHeight;

        label.setBounds(x, y, width, height);
        label.setText(text);
        label.setFont(font);

        if (newLine) {
            lineNumber++;
        }

        return label;
    }

    JButton createCountButton() {
        JButton button = new JButton();

        int x = marginLeftRight;
        int y = marginTopBottom + (elementHeight + spaceHeightInPercent) * lineNumber;
        int width = elementWidth;
        int height = elementHeight;

        button.setBounds(x, y, width, height);
        button.setText("Przelicz");
        button.setFont(font);
        button.setBackground(ColorManager.getElementColor());
        button.setBorder(null);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusable(false);
        button.addMouseListener(mouseListener);

        lineNumber++;

        return button;
    }

    JTextField createEmptyTextField() {
        JTextField textField = new JTextField();

        int x = marginLeftRight;
        int y = marginTopBottom + (elementHeight + spaceHeightInPercent) * lineNumber;
        int width = elementWidth;
        int height = elementHeight;

        textField.setBounds(x, y, width, height);
        textField.setFont(font);
        textField.setBackground(ColorManager.getBackgroundColor());
        textField.setBorder(null);

        lineNumber++;

        return textField;
    }

    JTextField createEmptyTextField(int width, int x) {
        JTextField textField = new JTextField();

        x += marginLeftRight;
        int y = marginTopBottom + (elementHeight + spaceHeightInPercent) * lineNumber;
        int height = elementHeight;

        textField.setBounds(x, y, width, height);
        textField.setFont(font);
        textField.setBackground(ColorManager.getElementColor());
        textField.setBorder(null);

        return textField;
    }

    JComboBox<String> createDropDownList() {
        JComboBox<String> comboBox = new JComboBox<>();

        int x = marginLeftRight;
        int y = marginTopBottom + (elementHeight + spaceHeightInPercent) * lineNumber;
        int width = elementWidth;
        int height = elementHeight;

        comboBox.setBounds(x, y, width, height);
        comboBox.setFont(font);
        comboBox.setBackground(ColorManager.getElementColor());
        comboBox.setBorder(null);
        comboBox.setFocusable(false);

        lineNumber++;

        return comboBox;
    }

    int getElementWidth() {
        return elementWidth;
    }
}