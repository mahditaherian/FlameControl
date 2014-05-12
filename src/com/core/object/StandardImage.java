package com.core.object;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Mahdi
 */
public class StandardImage {

    private BufferedImage image;
    private int[][] histogram;

    public StandardImage(BufferedImage img) {
        this.image = img;
        updateHistogram();
        histogram = new int[3][3];
    }

    private void updateHistogram() {
        int imageHeight = image.getHeight();
        int imageWidth = image.getWidth();

//        RandomIter iterator = RandomIterFactory.create(image, null);
        // Get memory for a pixel and for the accumulator.
        double[] pixel = new double[3];
        clearHistogram();
        //make histogram
        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
//                iterator.getPixel(x, y, pixel);
                Color color = new Color(image.getRGB(x, y));
                histogram[0][color.getRed()]++;
                histogram[1][color.getGreen()]++;
                histogram[2][color.getBlue()]++;
            }
        }
    }

    public int[][] getHistogram() {
        return histogram;
    }

    public BufferedImage getImage() {
        return image;
    }

    private void clearHistogram() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                histogram[i][j] = 0;
            }
        }
    }

}
