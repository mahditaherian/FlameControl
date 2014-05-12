package com.core.object;

import com.core.FlameStateType;
import java.awt.image.BufferedImage;

/**
 *
 * @author Peyman
 */
public class Flame extends StandardImage {
    private FlameStateType stateType;

    public Flame(BufferedImage img) {
        super(img);
    }

    public void setStateType(FlameStateType flameState) {
        this.stateType = flameState;
    }
    
}
