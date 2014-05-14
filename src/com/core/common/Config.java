package com.core.common;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mahdi
 */
public final class Config {

    public static final int COLOR_LEVEL_QUANTIZATION = 3;

    public static final double RED_COLOR_COEFFICIENT = 10d;
    public static final double GREEN_COLOR_COEFFICIENT = 10d;
    public static final double BLUE_COLOR_COEFFICIENT = 0.1d;

    public static final double SAFE_COLOR_SIMILARITY = 0.9d;
    public static final double CAUTION_COLOR_SIMILARITY = 0.5d;

    public static final double SAFE_SIZE_SIMILARITY = 0.9d;
    public static final double CAUTION_SIZE_SIMILARITY = 0.8d;

    public static final List<Color> FLAME_BOUND_COLORS = new ArrayList<>();

    static {
        FLAME_BOUND_COLORS.add(new Color(0,0,2));
        FLAME_BOUND_COLORS.add(new Color(1,1,2));
        FLAME_BOUND_COLORS.add(new Color(2,2,2));
    }
}
