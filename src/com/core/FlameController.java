package com.core;

import com.core.comparator.TestResult;
import com.core.object.Flame;
import com.core.object.StandardImage;
import com.core.processor.Criteria;
import com.core.processor.ImageProcessor;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mahdi
 */
public class FlameController {

    private final Criteria criteria;

    public FlameController(Criteria criteria) {
        this.criteria = criteria;
    }

    public void run() {
        for (int i = 0; i < criteria.getSamplingCount(); i++) {
            BufferedImage image = criteria.getProvider().next();
            List<Flame> flames = getFlames(image);
        }
    }

    public List<Flame> getFlames(BufferedImage image) {
        StandardImage standardImage = ImageProcessor.standardize(image);
        List<Flame> flames = ImageProcessor.getFlames(standardImage);
        for (Flame flame : flames) {
            TestResult result = criteria.getComparator().compare(flame, criteria.getReference());
            flame.setTestResult(result);
        }
        return flames;
    }

}
