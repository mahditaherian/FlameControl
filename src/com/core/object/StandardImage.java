package com.core.object;

import com.core.common.Config;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Mahdi
 */
public class StandardImage {

    private BufferedImage image;
    private Histogram histogram;
    private int width;
    private int height;
    private Color[][] pixels;

    public StandardImage(BufferedImage img) {
        this.image = img;
        histogram = new Histogram(Config.COLOR_LEVEL_QUANTIZATION);
        width = img.getWidth();
        height = img.getHeight();
        pixels = new Color[width][height];
        updateHistogram();
    }

    private void updateHistogram() {
        histogram.reset();
        //make histogram
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = new Color(image.getRGB(x, y));
                pixels[x][y] = color;
                histogram.addColor(color);
            }
        }
    }

    public Color getPixel(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y > height) {
            throw new IllegalArgumentException("The entered pixel is out of image range.");
        }
        return pixels[x][y];
    }
    
    public Histogram getHistogram() {
        return histogram;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
