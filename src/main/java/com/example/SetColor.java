package com.example;

import java.awt.*;
import java.util.HashMap;

public class SetColor {
	// Funkcja zwraca losowo jeden kolor z palety barw
		public Color getRandomColor() {
			HashMap <Integer, Color> colors = new HashMap <Integer, Color> ();
				
			// Tworzenie mapy przykładowych kolorów
				int i = 0;
				colors.put(i++, new Color (158, 115, 148));
				colors.put(i++, new Color (255, 127, 80));
				colors.put(i++, new Color (100, 149, 237));
				colors.put(i++, new Color (233, 150, 122));
				colors.put(i++, new Color (143, 188, 143));
				colors.put(i++, new Color (255, 215, 0));
				colors.put(i++, new Color (205, 92, 92));
				colors.put(i++, new Color (240, 230, 140));
				colors.put(i++, new Color (173, 216, 230));
				colors.put(i++, new Color (144, 238, 144));
				colors.put(i++, new Color (255, 160, 122));
				colors.put(i++, new Color (255, 222, 173));
				colors.put(i++, new Color (250, 128, 114));
				colors.put(i++, new Color (216, 191, 216));
			
			return colors.get((int) (Math.random() * colors.size()));
		}
			
	// Funkcja przyciemnia lub objaœnia dany kolor o daną wartość
		public Color getChangedColor (Color color, int value) {
			int red, green, blue;
			
			red = color.getRed() + value;
			green = color.getGreen() + value;
			blue = color.getBlue() + value;
					
			red = red > 255 ? 255 : red;
			green = green > 255 ? 255 : green;
			blue = blue > 255 ? 255 : blue;
				
			red = red < 0 ? 0 : red;
			green = green < 0 ? 0 : green;
			blue = blue < 0 ? 0 : blue;
				
			return new Color (red, green, blue);
		}
}
