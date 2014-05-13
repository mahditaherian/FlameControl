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

    public StandardImage(BufferedImage img) {
        this.image = img;
        updateHistogram();
        width = img.getWidth();
        height = img.getHeight();
        histogram = new Histogram(Config.COLOR_LEVEL_QUANTIZATION);
    }

    private void updateHistogram() {
        int imageHeight = image.getHeight();
        int imageWidth = image.getWidth();

//        RandomIter iterator = RandomIterFactory.create(image, null);
        // Get memory for a pixel and for the accumulator.
        double[] pixel = new double[3];
        histogram.reset();
        //make histogram
        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
//                iterator.getPixel(x, y, pixel);
                Color color = new Color(image.getRGB(x, y));
                histogram.addColor(color);
            }
        }
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
