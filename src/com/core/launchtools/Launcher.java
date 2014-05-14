package com.core.launchtools;

import com.core.FlameController;
import com.core.comparator.ImageComparator;
import com.core.object.Flame;
import com.core.object.StandardImage;
import com.core.processor.Criteria;
import com.core.processor.ImageProcessor;
import com.core.provider.ImageProvider;
import com.core.provider.SavedImageProvider;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Mahdi
 */
public class Launcher {

    private static final String referenceImagePath = "/images/ref.jpg";
    private static final String savedImagesPath = "/images/flames/";

    public static void main(String[] args) {

        try {
            File file = new File("images/flames.png");
            BufferedImage read = ImageIO.read(file);
            StandardImage standardImage = ImageProcessor.standardize(read);
            ImageProcessor.getFlames(standardImage);
            if (2 * 2 == 4) {
                return;
            }
            ImageComparator imageComparator = new ImageComparator();
            BufferedImage refImg = ImageIO.read(new File(referenceImagePath));
            Flame reference = new Flame(refImg);
            ImageProvider imageProvider = new SavedImageProvider(new File(savedImagesPath));

            Criteria criteria = new Criteria(imageComparator, 1, 0, reference, imageProvider);

            FlameController flameController = new FlameController(criteria);
            flameController.run();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
