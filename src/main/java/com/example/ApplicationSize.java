package com.example;


import java.awt.*;

final class ApplicationSize {

    private static final Rectangle applicationBounds;

    static {
        Rectangle windowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

        Rectangle desktop = getDesktopSize(windowBounds);

        int width = (int) windowBounds.getWidth() / 2;
        int height = (int) windowBounds.getHeight() / 2;
        int x = (int) ((desktop.getWidth() - width) / 2);
        int y = (int) ((desktop.getHeight() - height) / 2);

        applicationBounds = new Rectangle(x, y, width, height);
    }


    private static Rectangle getDesktopSize(Rectangle windowBounds) {
        int width = (int) windowBounds.getWidth();
        int height = (int) windowBounds.getHeight();
        return new Rectangle(width, height);
    }

    static Rectangle getApplicationBounds() {
        return applicationBounds;
    }
}