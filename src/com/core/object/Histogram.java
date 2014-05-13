package com.core.object;

import java.awt.Color;

/**
 *
 * @author Mahdi
 */
public class Histogram {

    int level;
    private final int[] reds, greens, blues;

    public Histogram(int level) {
        reds = new int[level];
        greens = new int[level];
        blues = new int[level];
    }

    /**
     * add color into current histogram values.<br/>
     * Each color values must be greater than or equals zero and less than Color level 
     * set in the <b>Config</b>.
     * 
     * @param red
     * @param green
     * @param blue 
     */
    public void addColor(int red, int green, int blue) {
        if (!isValid(red) || !isValid(green) || !isValid(blue)) {
            throw new IllegalArgumentException("the color is out of range! colors must be between 0 and " + (level - 1));
        }
        reds[red]++;
        greens[green]++;
        blues[blue]++;
    }

    public void addColor(Color c) {
        addColor(c.getRed(), c.getGreen(), c.getBlue());
    }

    public void reset() {
        for (int i = 0; i < level; i++) {
            reds[i] = 0;
            greens[i] = 0;
            blues[i] = 0;
        }
    }

    public int getRed(int level) {
        if(!isValid(level)){
            throw new IllegalArgumentException("the color is out of range! colors must be between 0 and " + (level - 1));
        }
        return reds[level];
    }
    
    public int getGreen(int level){
        if (!isValid(level)) {
            throw new IllegalArgumentException("the color is out of range! colors must be between 0 and " + (level - 1));
        }
        return greens[level];
    }
    
    public int getBlue(int level){
        if (!isValid(level)) {
            throw new IllegalArgumentException("the color is out of range! colors must be between 0 and " + (level - 1));
        }
        return reds[level];
    }

    private boolean isValid(int c) {
        return c >= 0 && c < level;
    }
}
