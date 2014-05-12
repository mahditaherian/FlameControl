package com.core.processor;

import com.core.object.Flame;
import com.core.object.StandardImage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.List;

/**
 *
 * @author Mahdi
 */
public class ImageProcessor {

    public static StandardImage standardize(BufferedImage src) {
        BufferedImage image = deepCopy(src);
        //todo: resize, blur, quantize
//        StandardImage standardImage = new StandardImage(image);
        blurImage(image);
        quantizeImage(image);

        return new StandardImage(image);
    }

    private static BufferedImage blurImage(BufferedImage img) {
        GaussianFilter gaussianFilter = new GaussianFilter();
        gaussianFilter.filter(img, img);
        return img;
    }

    public static List<Flame> getFlames(StandardImage image) {
        throw new UnsupportedOperationException();
    }

    private static void quantizeImage(BufferedImage image) {
        Color color;
        int r, g, b, rgb;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                color = new Color(image.getRGB(x, y));
                r = (int) Math.round((double) color.getRed() / (double) 128);
                g = (int) Math.round((double) color.getGreen() / (double) 128);
                b = (int) Math.round((double) color.getBlue() / (double) 128);
                rgb = new Color(r, g, b).getRGB();
                image.setRGB(x, y, rgb);
            }
        }
    }

    private static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

}
