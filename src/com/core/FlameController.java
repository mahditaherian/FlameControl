package com.core;

import com.core.object.Flame;
import com.core.object.StandardImage;
import com.core.processor.Criteria;
import com.core.processor.ImageProcessor;
import com.core.provider.ImageProvider;
import java.awt.Image;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mahdi
 */
public class FlameController {

    private Criteria criteria;
    private ImageProvider provider;
    private ImageProcessor processor;

    public FlameController(ImageProvider provider, Criteria criteria) {
        this.criteria = criteria;
        this.provider = provider;
        processor = new ImageProcessor();
    }

    public void run() {
        for (int i = 0; i < criteria.getSamplingCount(); i++) {
            try {
                Image image = provider.next();
                
                
                StandardImage standardImage = ImageProcessor.standardize(image);
                List<Flame> flames = ImageProcessor.getFlames(standardImage);
                
                for(Flame flame : flames){
                   FlameStateType stateType = criteria.getComparator().compare(flame , criteria.getReference());
                   flame.setStateType(stateType);
                }
                
                Thread.sleep(criteria.getSamplingDelay()*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(FlameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
