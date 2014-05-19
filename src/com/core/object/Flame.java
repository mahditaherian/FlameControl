package com.core.object;

import com.core.FlameStateType;
import com.core.comparator.TestResult;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Mahdi
 */
public class Flame extends StandardImage {

    private Rectangle bound;
    private int area;
    private TestResult result;

    public Flame(BufferedImage img) {
        this(img, new Rectangle(0, 0, img.getWidth(), img.getHeight()));
    }

    public Flame(BufferedImage img, Rectangle bound) {
        super(img);
        updateArea();
        this.bound = bound;
    }
    
    public void setTestResult(TestResult result){
        this.result = result;
    }

    public TestResult getResult() {
        return result;
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
