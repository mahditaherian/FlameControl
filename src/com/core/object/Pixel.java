/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.object;

import java.awt.Color;

/**
 *
 * @author Peyman
 */
public class Pixel extends Color {

    public Pixel(int r, int g, int b) {
        super(r, g, b);
    }

    public Pixel(int rgb) {
        super(rgb);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Color)) {
            return false;
        }
        final Color color = (Color) obj;
        return color.getRed() == getRed() && color.getGreen() == getGreen() && color.getBlue() == getBlue();
    }

}
