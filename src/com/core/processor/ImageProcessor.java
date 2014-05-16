package com.core.processor;

import com.core.common.Config;
import com.core.object.Flame;
import com.core.object.Pixel;
import com.core.object.StandardImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Mahdi
 */
public class ImageProcessor {

    public static StandardImage standardize(BufferedImage src) {
        BufferedImage image = deepCopy(src);
        //todo: resize, blur, quantize
//        StandardImage standardImage = new StandardImage(image);

//        image = resizeImage(image);
        blurImage(image);
        File path = new File("out/blur.bmp");
        try {
            ImageIO.write(image, "bmp", path);
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        quantizeImage(image);
        path = new File("out/quantized.bmp");
        try {
            final BufferedImage deepCopy = deepCopy(image);
            ImageIO.write(image, "bmp", path);
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new StandardImage(image);
    }

    private static BufferedImage blurImage(BufferedImage img) {
        GaussianFilter gaussianFilter = new GaussianFilter(Config.DEFINE_AS_NOISE_SIZE);
        gaussianFilter.filter(img, img);
        return img;
    }

    public static List<Flame> getFlames(StandardImage image) {
        Flame flame;
        List<Flame> flames = new ArrayList<>();
        Rectangle bound;
        boolean flameStarted;
        boolean flameEnded = false;
        int bx = 0, by = image.getHeight(), y1 = 0, y2 = 0, w = 1, h = 1;
        for (int x = 0; x < image.getWidth(); x++) {
            flameStarted = false;
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel pixel = image.getPixel(x, y);
                if (Config.FLAME_BOUND_COLORS.contains(pixel)) {
                    if (!flameStarted) {
                        flameStarted = true;
                        y1 = y;
                        y2 = y;
                    } else {
                        y2 = y;
                    }
                }
            }

            if (!flameStarted) {
                if (flameEnded) {
                    if (h < Config.DEFINE_AS_NOISE_SIZE || w < Config.DEFINE_AS_NOISE_SIZE) {
                        //this is noise.....
                    } else {
                        bound = new Rectangle(bx, by, w, h);
                        BufferedImage bounded = crop(image.getImage(), bound);
                        flame = new Flame(bounded, bound);
                        flames.add(flame);
                    }
                    bx = 0;
                    by = image.getHeight();
                    y1 = 0;
                    y2 = 0;
                    w = 1;
                    h = 1;
                }
                bx = x;
                flameEnded = false;
            } else {
                flameEnded = true;
                by = Math.min(by, y1);
                h = Math.max(h, y2 - y1);
                w = x - bx + 1;
            }
        }
        return flames;
    }

    public static BufferedImage crop(BufferedImage src, Rectangle bound) {
        BufferedImage dest = src.getSubimage(bound.x, bound.y, bound.width, bound.height);
        return dest;
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
                Color color1 = new Color(r, g, b);
                rgb = color1.getRGB();
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

    public static BufferedImage resizeImage(BufferedImage image) {
        image = toBufferedImage(image.getScaledInstance((int) (image.getWidth() * Config.SCALE_IMAGE),
                (int) (image.getHeight() * Config.SCALE_IMAGE), Image.SCALE_SMOOTH));
        return image;
    }

    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

}
