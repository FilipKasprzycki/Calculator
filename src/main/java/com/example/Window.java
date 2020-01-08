package com.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Window extends JFrame {

    private AddField addField;

    // Parametry pulpitu
    private int desktopWidth;
    private int desktopHeight;

    // Parametry okna
    private int windowWidth;
    private int windowHeight;

    // Elementy okna
    private JComboBox<String> list;
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

            switch(list.getSelectedIndex()) {
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
                    switch(mode) {
                        case "DMS":
                            labelText = calculate.dmsToDm(dmsDegrees.getText(), dmsMinutes.getText(), dmsSeconds.getText());
                            break;
                        case "DM":
                            labelText = calculate.dmToDms(dmsDegrees.getText(), dmsMinutes.getText());
                            break;
                    }

                    // Upewnienie si�, �e tekst nie jest wy�wietlany z ustawieniami komunikatu o b��dzie
                    if(label.getForeground() == Color.RED) {
                        label.setForeground(Color.BLACK);
                        label.setFont(new Font(label.getFont().getName(), Font.PLAIN, label.getFont().getSize()));
                    }
                }
                catch (NumberFormatException exception) {
                    // Komunikat o b��dzie - pogrubienie i zmiana koloru tekstu
                    labelText = "Niew�a�ciwy format danych";
                }
                label.setText(labelText);
            }
        }
    };

    // Inne
    private Calculations calculate = new Calculations();

    public Window() {
        // Ikonka programu
        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/icon.png")));
        }
        catch(Exception e) {}

        // Pobranie wymiar�w pulpitu
        desktopWidth = (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
        desktopHeight = (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight();

        // Ustawianie parametr�w okna
        windowWidth = (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth() / 2;
        windowHeight = (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight() / 2;
        setBounds((desktopWidth - windowWidth) / 2, (desktopHeight - windowHeight) / 2, windowWidth, windowHeight);
        setTitle("Przelicznik");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Obliczanie po�o�enia element�w:
        setVisible(true);
        addField = new AddField(getContentPane().getWidth(), getContentPane().getHeight(), 4, 5, 5, 7);
        getContentPane().setBackground(addField.getBackgroundColor());

        // Dodanie listy
        list = addField.createJComboBox();
        list.addItem("stopnie : minuty : sekundy");
        list.addItem("stopnie : minuty");
        list.addActionListener(listActionListener);
        add(list);

        // Dodanie pola do wpisywania liczby
        int widthUnit = (int) (addField.getElementWidth() / 18);
        int textFieldWidth = 5 * widthUnit;
        int characterWidth = widthUnit;

        dmsDegrees = addField.createJTextField(textFieldWidth, 0 * 6 * widthUnit, false);
        add(dmsDegrees);
        dmsDegreeCharacter = addField.createJLabel("*", characterWidth, 1 * 6 * widthUnit - widthUnit, false);
        add(dmsDegreeCharacter);

        dmsMinutes = addField.createJTextField(textFieldWidth, 1 * 6 * widthUnit, false);
        add(dmsMinutes);
        dmsMinutesCharacter = addField.createJLabel("\"", characterWidth, 2 * 6 * widthUnit - widthUnit, false);
        add(dmsMinutesCharacter);

        if ("DMS".equals(mode)) {
            dmsSeconds = addField.createJTextField(textFieldWidth, 2 * 6 * widthUnit, false);
            add(dmsSeconds);
            dmsSecondsCharacter = addField.createJLabel("'", characterWidth, 3 * 6 * widthUnit - widthUnit, true);
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
}