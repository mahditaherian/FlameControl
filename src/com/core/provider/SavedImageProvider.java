package com.core.provider;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * @author Mahdi
 */
public class SavedImageProvider extends ImageProvider {

    private File path;
    private List<BufferedImage> images;
    private int lastIndex = -1;

    public SavedImageProvider(File pathToImages) {
        if (pathToImages == null || pathToImages.exists() || !pathToImages.isDirectory()) {
            throw new IllegalArgumentException("The path you entered is not a valid directory!");
        }

        this.path = pathToImages;

    }

    public SavedImageProvider(String pathToImages) {
        this(new File(pathToImages));
    }

    public void updatePathImages() {
        for (File file : path.listFiles()) {
            if (file.isFile() && file.canRead() && file.canWrite()) {
                try {
                    images.add(ImageIO.read(file));
                } catch (IOException ex) {
                    Logger.getLogger(SavedImageProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public BufferedImage next() {
        return images.get(++lastIndex);
    }

}
