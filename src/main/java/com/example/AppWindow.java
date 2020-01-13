package com.example;

import javax.swing.*;
import java.awt.event.ActionListener;

final class AppWindow extends JFrame {

    private Mode mode;
    private JTextField dmsDegrees;
    private JTextField dmsMinutes;
    private JTextField dmsSeconds;
    private JTextField resultField;
    private JLabel dmsSecondsCharacter;
    private String labelText;

    AppWindow() {
        mode = Mode.DMS;
        labelText = "Wynik";

        super.setBounds(ApplicationSize.getApplicationBounds());
        super.setTitle("Calculator");
        super.setLayout(null);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setVisible(true);
        super.getContentPane().setBackground(ColorManager.getBackgroundColor());

        FieldManager fieldManager = new FieldManager(getContentPane().getWidth(), getContentPane().getHeight());

        createDropDownList(fieldManager);
        createInsertFields(fieldManager);
        createResultField(fieldManager);
        createCountButton(fieldManager);
    }

    private void createInsertFields(FieldManager fieldManager) {
        int widthUnit = fieldManager.getElementWidth() / 18;
        int textFieldWidth = 5 * widthUnit;

        dmsDegrees = fieldManager.createEmptyTextField(textFieldWidth, 0);
        super.add(dmsDegrees);
        JLabel dmsDegreeCharacter = fieldManager.createTextLabel("*", widthUnit, 6 * widthUnit - widthUnit, false);
        super.add(dmsDegreeCharacter);

        dmsMinutes = fieldManager.createEmptyTextField(textFieldWidth, 6 * widthUnit);
        super.add(dmsMinutes);
        JLabel dmsMinutesCharacter = fieldManager.createTextLabel("\"", widthUnit, 12 * widthUnit - widthUnit, false);
        super.add(dmsMinutesCharacter);

        if (Mode.DMS.equals(mode)) {
            dmsSeconds = fieldManager.createEmptyTextField(textFieldWidth, 12 * widthUnit);
            super.add(dmsSeconds);
            dmsSecondsCharacter = fieldManager.createTextLabel("'", widthUnit, 12 * widthUnit - widthUnit, true);
            super.add(dmsSecondsCharacter);
        }
    }

    private void createDropDownList(FieldManager fieldManager) {
        JComboBox<String> dropDownList = fieldManager.createDropDownList();
        dropDownList.addItem("stopnie : minuty : sekundy");
        dropDownList.addItem("stopnie : minuty");

        ActionListener listActionListener = e -> {
            switch (dropDownList.getSelectedIndex()) {
                case 0:
                    mode = Mode.DMS;
                    dmsSeconds.setEnabled(true);
                    dmsSecondsCharacter.setEnabled(true);
                    dmsSeconds.setVisible(true);
                    dmsSecondsCharacter.setVisible(true);
                    break;
                case 1:
                    mode = Mode.DM;
                    dmsSeconds.setEnabled(false);
                    dmsSecondsCharacter.setEnabled(false);
                    dmsSeconds.setVisible(false);
                    dmsSecondsCharacter.setVisible(false);
                    break;
            }
        };

        dropDownList.addActionListener(listActionListener);

        super.add(dropDownList);
    }

    private void createCountButton(FieldManager fieldManager) {
        JButton countButton = fieldManager.createCountButton();

        ActionListener buttonActionListener = e -> {
            if (e.getSource() == countButton) {
                try {
                    switch (mode) {
                        case DMS:
                            labelText = Converter.dmsToDm(dmsDegrees.getText(), dmsMinutes.getText(), dmsSeconds.getText());
                            break;
                        case DM:
                            labelText = Converter.dmToDms(dmsDegrees.getText(), dmsMinutes.getText());
                            break;
                    }

                } catch (NumberFormatException exception) {
                    labelText = "Niewłaściwy format danych";
                }
                resultField.setText(labelText);
            }
        };

        countButton.addActionListener(buttonActionListener);
        super.add(countButton);
    }

    private void createResultField(FieldManager fieldManager) {
        resultField = fieldManager.createEmptyTextField();
        resultField.setText(labelText);
        resultField.setEditable(false);

        super.add(resultField);
    }
}