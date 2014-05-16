package com.core.object;

import com.core.FlameStateType;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Mahdi
 */
public class Flame extends StandardImage {

    private FlameStateType stateType;
    private Rectangle bound;
    private int area;

    public Flame(BufferedImage img) {
        this(img, new Rectangle(0, 0, img.getWidth(), img.getHeight()));
    }

    public Flame(BufferedImage img, Rectangle bound) {
        super(img);
        updateArea();
        this.bound = bound;
    }

    private int updateArea() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                Color c = getPixel(x, y);
                if (c.getRed() > 0 || c.getGreen() > 0 || c.getBlue() > 0) {
                    area++;
                }
            }
        }
        return area;
    }

    public void setStateType(FlameStateType flameState) {
        this.stateType = flameState;
    }

    public FlameStateType getStateType() {
        return stateType;
    }

    public Rectangle getBound() {
        return bound;
    }

    public int getArea() {
        return area;
    }
    
    public Point getPosition() {
        return bound.getLocation();
    }

}
