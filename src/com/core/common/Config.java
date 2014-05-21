package com.core.common;

import com.core.object.Pixel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mahdi
 */
public final class Config {

    public static final File DEFAULT_IMAGES_PATH = new File("images/");
    public static final File DEFAULT_REFERENCE = new File("images/reference/ref1.jpg");
    public static final String[] VALID_IMAGE_EXTENSIONS = new String[]{"jpg", "png", "bmp"};

    public static final int COLOR_LEVEL_QUANTIZATION = 3;

    public static final float SCALE_IMAGE = 0.5f;

    public static int DEFINE_AS_NOISE_SIZE = (int) (50 * SCALE_IMAGE);

    public static final double RED_COLOR_COEFFICIENT = 10d;
    public static final double GREEN_COLOR_COEFFICIENT = 10d;
    public static final double BLUE_COLOR_COEFFICIENT = 0.1d;
    
    public static final int CAMERA_IN_USE = 1;

    public static double SAFE_COLOR_SIMILARITY = 0.75d;
    public static double CAUTION_COLOR_SIMILARITY = 0.5d;

    public static double SAFE_SIZE_SIMILARITY = 0.75d;
    public static double CAUTION_SIZE_SIMILARITY = 0.5d;

    public static final double SAFE_AREA_SIMILARITY = 0.8d;
    public static final double CAUTION_AREA_SIMILARITY = 0.5d;

    public static final List<Pixel> FLAME_BOUND_COLORS = new ArrayList<>();

    static {
        FLAME_BOUND_COLORS.add(new Pixel(0, 0, 1));
        FLAME_BOUND_COLORS.add(new Pixel(0, 0, 2));
        FLAME_BOUND_COLORS.add(new Pixel(1, 1, 2));
        FLAME_BOUND_COLORS.add(new Pixel(2, 2, 2));
    }
}
