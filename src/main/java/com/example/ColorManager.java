package com.example;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ColorManager {

    private static final Map<Integer, Color> colors;
    private static final Color backgroundColor;
    private static final Color elementColor;
    private static final Color hoverColor;

    static {
        colors = new HashMap<>();

        int i = 0;
        colors.put(i++, new Color(158, 115, 148));
        colors.put(i++, new Color(255, 127, 80));
        colors.put(i++, new Color(100, 149, 237));
        colors.put(i++, new Color(233, 150, 122));
        colors.put(i++, new Color(143, 188, 143));
        colors.put(i++, new Color(255, 215, 0));
        colors.put(i++, new Color(205, 92, 92));
        colors.put(i++, new Color(240, 230, 140));
        colors.put(i++, new Color(173, 216, 230));
        colors.put(i++, new Color(144, 238, 144));
        colors.put(i++, new Color(255, 160, 122));
        colors.put(i++, new Color(255, 222, 173));
        colors.put(i++, new Color(250, 128, 114));
        colors.put(i, new Color(216, 191, 216));

        backgroundColor = random();
        elementColor = ColorManager.changeColor(20);
        hoverColor = ColorManager.changeColor(40);
    }

    private static Color changeColor(int value) {
        int red = backgroundColor.getRed() + value;
        int green = backgroundColor.getGreen() + value;
        int blue = backgroundColor.getBlue() + value;

        red = red > 255 ? 255 : red;
        green = green > 255 ? 255 : green;
        blue = blue > 255 ? 255 : blue;

        red = red < 0 ? 0 : red;
        green = green < 0 ? 0 : green;
        blue = blue < 0 ? 0 : blue;

        return new Color(red, green, blue);
    }

    private static Color random() {
        return colors.get((int) (Math.random() * colors.size()));
    }

    public static Color getBackgroundColor() {
        return backgroundColor;
    }

    public static Color getElementColor() {
        return elementColor;
    }

    public static Color getHoverColor() {
        return hoverColor;
    }
}