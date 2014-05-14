package com.core.object;

import com.core.FlameStateType;
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

    public Flame(BufferedImage img) {
        this(img, new Rectangle(0, 0, img.getWidth(), img.getHeight()));
    }

    public Flame(BufferedImage img, Rectangle bound) {
        super(img);
        this.bound = bound;
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

    public Point getPosition() {
        return bound.getLocation();
    }

}
