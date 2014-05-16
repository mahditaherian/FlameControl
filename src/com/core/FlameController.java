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
            try {
                BufferedImage image = criteria.getProvider().next();

                StandardImage standardImage = ImageProcessor.standardize(image);
                List<Flame> flames = ImageProcessor.getFlames(standardImage);//todo should implements

                for (Flame flame : flames) {
                    TestResult result = criteria.getComparator().compare(flame, criteria.getReference());
                    flame.setStateType(result.getFlameState());
                }
                
                
                Thread.sleep(criteria.getSamplingDelay() * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(FlameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
