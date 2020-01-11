package com.example.listeners;

import com.example.ColorManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ColorfulMouseListener implements MouseListener {

    private final Color elementColor;
    private final Color hoverColor;

    public ColorfulMouseListener() {
        this.elementColor = ColorManager.getElementColor();
        this.hoverColor = ColorManager.getHoverColor();
    }

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
}