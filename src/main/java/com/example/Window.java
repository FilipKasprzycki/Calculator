package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class Window extends JFrame {

    private AddField addField;

    // Elementy okna
    private JComboBox<String> fieldList;
    private String mode = "DMS";
    private JTextField dmsDegrees;
    private JTextField dmsMinutes;
    private JTextField dmsSeconds;
    private JTextField label;
    private JLabel dmsDegreeCharacter;
    private JLabel dmsMinutesCharacter;
    private JLabel dmsSecondsCharacter;
    private String labelText = "Wynik";
    private JButton countButton;

    // Listenery
    private ActionListener listActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            switch (fieldList.getSelectedIndex()) {
                case 0:
                    mode = "DMS";
                    dmsSeconds.setEnabled(true);
                    dmsSecondsCharacter.setEnabled(true);
                    dmsSeconds.setVisible(true);
                    dmsSecondsCharacter.setVisible(true);
                    break;
                case 1:
                    mode = "DM";
                    dmsSeconds.setEnabled(false);
                    dmsSecondsCharacter.setEnabled(false);
                    dmsSeconds.setVisible(false);
                    dmsSecondsCharacter.setVisible(false);
                    break;
            }
        }
    };

    private ActionListener buttonActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Wy�wietlanie wyniku
            if (e.getSource() == countButton) {
                try {
                    switch (mode) {
                        case "DMS":
                            labelText = Converter.dmsToDm(dmsDegrees.getText(), dmsMinutes.getText(), dmsSeconds.getText());
                            break;
                        case "DM":
                            labelText = Converter.dmToDms(dmsDegrees.getText(), dmsMinutes.getText());
                            break;
                    }

                    // Upewnienie si�, �e tekst nie jest wy�wietlany z ustawieniami komunikatu o b��dzie
                    if (label.getForeground() == Color.RED) {
                        label.setForeground(Color.BLACK);
                        label.setFont(new Font(label.getFont().getName(), Font.PLAIN, label.getFont().getSize()));
                    }
                } catch (NumberFormatException exception) {
                    // Komunikat o b��dzie - pogrubienie i zmiana koloru tekstu
                    labelText = "Niew�a�ciwy format danych";
                }
                label.setText(labelText);
            }
        }
    };

    Window() {
        setApplicationSize();
        super.setTitle("Calculator");
        super.setLayout(null);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setVisible(true);
        super.getContentPane().setBackground(ColorManager.getBackgroundColor());

        // Obliczanie po�o�enia element�w:
        addField = new AddField(getContentPane().getWidth(), getContentPane().getHeight(), 4, 5, 5, 7);

        // Dodanie listy
        fieldList = addField.createJComboBox();
        fieldList.addItem("stopnie : minuty : sekundy");
        fieldList.addItem("stopnie : minuty");
        fieldList.addActionListener(listActionListener);
        super.add(fieldList);

        // Dodanie pola do wpisywania liczby
        int widthUnit = addField.getElementWidth() / 18;
        int textFieldWidth = 5 * widthUnit;

        dmsDegrees = addField.createJTextField(textFieldWidth, 0, false);
        add(dmsDegrees);
        dmsDegreeCharacter = addField.createJLabel("*", widthUnit, 6 * widthUnit - widthUnit, false);
        add(dmsDegreeCharacter);

        dmsMinutes = addField.createJTextField(textFieldWidth, 6 * widthUnit, false);
        add(dmsMinutes);
        dmsMinutesCharacter = addField.createJLabel("\"", widthUnit, 2 * 6 * widthUnit - widthUnit, false);
        add(dmsMinutesCharacter);

        if ("DMS".equals(mode)) {
            dmsSeconds = addField.createJTextField(textFieldWidth, 2 * 6 * widthUnit, false);
            add(dmsSeconds);
            dmsSecondsCharacter = addField.createJLabel("'", widthUnit, 3 * 6 * widthUnit - widthUnit, true);
            add(dmsSecondsCharacter);
        }

        // Dodanie pola wy�wietlaj�cego wynik
        label = addField.createJTextField();
        label.setText(labelText);
        label.setEditable(false);
        add(label);

        // Dodanie przycisku przeliczania
        countButton = addField.createJButton("Przelicz");
        countButton.addActionListener(buttonActionListener);
        add(countButton);
    }

    private void setApplicationSize() {
        ApplicationSize size = new ApplicationSize();
        super.setBounds(size.getApplicationBounds());
    }
}