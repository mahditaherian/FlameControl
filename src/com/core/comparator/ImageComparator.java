package com.core.comparator;

import com.core.FlameStateType;
import com.core.common.Config;
import com.core.object.Flame;
import com.core.object.Histogram;
import java.awt.Color;

/**
 * @author Mahdi
 */
public class ImageComparator extends Comparator {

    @Override
    public FlameStateType compare(Flame flame, Flame reference) {
        double sizeSimilarity = sizeSimilarityValue(flame, reference);
        if (sizeSimilarity < Config.CAUTION_SIZE_SIMILARITY) {
            return FlameStateType.DANGER;
        }

        double colorSimilarity = colorSimilarityValue(flame, reference);
        if (colorSimilarity < Config.CAUTION_COLOR_SIMILARITY) {
            return FlameStateType.DANGER;
        }

        double areaSimilarity = areaSimilarityValue(flame, reference);
        if (areaSimilarity < Config.CAUTION_AREA_SIMILARITY) {
            return FlameStateType.DANGER;
        }

        if (colorSimilarity > Config.SAFE_COLOR_SIMILARITY && sizeSimilarity > Config.SAFE_SIZE_SIMILARITY && areaSimilarity > Config.SAFE_AREA_SIMILARITY) {
            return FlameStateType.SAFE;
        }

        return FlameStateType.CAUTION;
    }

    private double sizeSimilarityValue(Flame flame, Flame reference) {
        double w1 = flame.getWidth();
        double w2 = reference.getWidth();
        double h1 = flame.getHeight();
        double h2 = reference.getHeight();

        double s1 = h1 / w1;
        double s2 = h2 / w2;

        double val = Math.min(s1, s2) / Math.max(s1, s2);
        return val;
    }

    private double colorSimilarityValue(Flame flame, Flame reference) {
        Histogram src = flame.getHistogram();
        Histogram ref = reference.getHistogram();
        double refValue = getHistogramValue(ref) / reference.getArea();
        double srcValue = getHistogramValue(src) / flame.getArea();
        double colorSimilarity = Math.min(refValue, srcValue) / Math.max(refValue, srcValue);
        return colorSimilarity;
    }

    private int getHistogramValue(Histogram histogram) {
        int value = 0;
        for (int i = 0; i < Config.COLOR_LEVEL_QUANTIZATION; i++) {
            value += histogram.getRed(i) * Config.RED_COLOR_COEFFICIENT;
            value += histogram.getGreen(i) * Config.GREEN_COLOR_COEFFICIENT;
            value += histogram.getBlue(i) * Config.BLUE_COLOR_COEFFICIENT;

        }
        return value;
    }

    private double areaSimilarityValue(Flame flame, Flame reference) {
        int getAreaFlame = flame.getArea();
        double scale = reference.getHeight() / flame.getHeight();
        int getAreaReference = reference.getArea();
        double similarity = Math.min(getAreaFlame * scale * scale, getAreaReference) / Math.max(getAreaFlame * scale * scale, getAreaReference);
        return similarity;
    }

}
