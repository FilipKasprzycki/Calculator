package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
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
		
	// Kolory elementów
		private SetColor setColor;
		private static Color backgroundColor;
		private static Color elementColor;
		private static Color hoverColor;
		
	//Listener
		private MouseListener mouseListener;
		
	
	public AddField(int windowWidth, int windowHeight, int numberOfLines, int marginLeftRight, int marginTopBotton, int spaceHeight) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.numberOfLines = numberOfLines;
		this.marginLeftRight = marginLeftRight; // Musi być podane w procentach
		this.marginTopBottom = marginTopBotton; // Musi być podane w procentach
		this.spaceHeight = spaceHeight; // Musi być podane w procentach
		countParameters();
		setColor = new SetColor();
		backgroundColor = setColor.getRandomColor();
		elementColor = setColor.getChangedColor(backgroundColor, 20);
		hoverColor = setColor.getChangedColor(backgroundColor, 40);
		
		mouseListener = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				((JComponent) arg0.getSource()).setBackground(hoverColor);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				((JComponent) arg0.getSource()).setBackground(hoverColor);				
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				((JComponent) arg0.getSource()).setBackground(elementColor);
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				((JComponent) arg0.getSource()).setBackground(hoverColor);
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				((JComponent) arg0.getSource()).setBackground(hoverColor);
			}			
		};
	}
	
		
	public void countParameters() {
		marginLeftRight *= (int) (windowWidth / 100);
		marginTopBottom *= (int) (windowHeight / 100);
		spaceHeight *= (int) (windowHeight / 100);
		elementWidth = windowWidth - (2 * marginLeftRight);
		elementHeight = (int) ((windowHeight - (2 * marginTopBottom) - (spaceHeight * (numberOfLines - 1))) / numberOfLines);
		font = new Font(Font.SANS_SERIF, Font.PLAIN, (int) (elementHeight * .5));
	}
	
	public JLabel createJLabel(String text) {
		JLabel label = new JLabel();
		
		// Dane pomocnicze
			int x = marginLeftRight;
			int y = marginTopBottom + (elementHeight + spaceHeight) * number;
			int width = elementWidth;
			int height = elementHeight;
				
		label.setBounds(x, y, width, height);
		label.setText(text);
		label.setFont(font);
				
		// Przejście do następnej linii
			number++;
		
		return label;
	}
	
	public JLabel createJLabel(String text, int width, int x, boolean newLine) {
		JLabel label = new JLabel();
		
		// Dane pomocnicze
			x += marginLeftRight;
			int y = marginTopBottom + (elementHeight + spaceHeight) * number;
			int height = elementHeight;
				
		label.setBounds(x, y, width, height);
		label.setText(text);
		label.setFont(font);
		
		if(newLine) {
			number++;
		}
		
		return label;
	}
	
	public JButton createJButton(String text) {
		JButton button = new JButton();	
		
		// Dane pomocnicze
			int x = marginLeftRight;
			int y = marginTopBottom + (elementHeight + spaceHeight) * number;
			int width = elementWidth;
			int height = elementHeight;
		
		button.setBounds(x, y, width, height);
		button.setText(text);
		button.setFont(font);
		button.setBackground(elementColor);
		button.setBorder(null);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setFocusable(false);
		button.addMouseListener(mouseListener);
		
		// Przejście do następnej linii
			number++;
		
		return button;		
	}
	
	public JTextField createJTextField() {
		JTextField textField = new JTextField();
		
		// Dane pomocnicze
			int x = marginLeftRight;
			int y = marginTopBottom + (elementHeight + spaceHeight) * number;
			int width = elementWidth;
			int height = elementHeight;
				
		textField.setBounds(x, y, width, height);
		textField.setFont(font);
		textField.setBackground(backgroundColor);
		textField.setBorder(null);
				
		// Przejście do następnej linii
			number++;
		
		return textField;
	}
	
	public JTextField createJTextField(int width, int x, boolean newLine) {
		JTextField textField = new JTextField();
		
		// Dane pomocnicze
			x += marginLeftRight;
			int y = marginTopBottom + (elementHeight + spaceHeight) * number;
			int height = elementHeight;
				
		textField.setBounds(x, y, width, height);
		textField.setFont(font);
		textField.setBackground(elementColor);
		textField.setBorder(null);
		
		if(newLine) {
			number++;
		}
		
		return textField;
	}
	
	public JComboBox <String> createJComboBox() {
		JComboBox<String> comboBox = new JComboBox<String>();
		
		// Dane pomocnicze
			int x = marginLeftRight;
			int y = marginTopBottom + (elementHeight + spaceHeight) * number;
			int width = elementWidth;
			int height = elementHeight;
				
		comboBox.setBounds(x, y, width, height);
		comboBox.setFont(font);
		comboBox.setBackground(elementColor);
		comboBox.setBorder(null);
		comboBox.setFocusable(false);
				
		// Przejście do następnej linii
			number++;
		
		return comboBox;
	}	
	
	public void nextLine() {
		number++;
	}
	 
	public int getElementWidth() {
		return elementWidth;
	}
	
	public Color getBackgroundColor() {
		return backgroundColor;
	}
}
