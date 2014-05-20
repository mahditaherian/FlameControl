package com.core.comparator;

import com.core.FlameStateType;
import com.core.common.Config;
import com.core.object.Flame;
import com.core.object.Histogram;

/**
 * @author Mahdi
 */
public class ImageComparator extends Comparator {

    @Override
    public TestResult compare(Flame flame, Flame reference) {
        TestResult result = new TestResult();
        double sizeSimilarity = 1;
        double colorSimilarity = 1;
        double areaSimilarity = 1;
        sizeSimilarity = sizeSimilarityValue(flame, reference);
        colorSimilarity = colorSimilarityValue(flame, reference);
//        areaSimilarity = areaSimilarityValue(flame, reference);
        result.setSizeSimilarity(sizeSimilarity);
        result.setColorSimilarity(colorSimilarity);
        result.setAreaSimilarity(areaSimilarity);
        result.setSimilarity(sizeSimilarity * colorSimilarity * areaSimilarity);
        if (sizeSimilarity < Config.CAUTION_SIZE_SIMILARITY) {
            result.setFlameState(FlameStateType.DANGER);
        } else if (colorSimilarity < Config.CAUTION_COLOR_SIMILARITY) {
            result.setFlameState(FlameStateType.DANGER);
        } else //        if (areaSimilarity < Config.CAUTION_AREA_SIMILARITY) {
        //            return FlameStateType.DANGER;
        //        }
        if (colorSimilarity >= Config.SAFE_COLOR_SIMILARITY && sizeSimilarity >= Config.SAFE_SIZE_SIMILARITY && areaSimilarity >= Config.SAFE_AREA_SIMILARITY) {
            result.setFlameState(FlameStateType.SAFE);
        } else {
            result.setFlameState(FlameStateType.CAUTION);
        }

        return result;
    }

//    private int getReds(Flame flame) {
////        for(int i=1;i<Config.COLOR_LEVEL_QUANTIZATION;i++){
//        int rs = flame.getHistogram().getRed(1);
//        int gs = flame.getHistogram().getBlue(1);
//        int bs = flame.getHistogram().getGreen(1);
//        int ms = (rs + bs + gs);
//        ms *= 128;
//        rs = flame.getHistogram().getRed(1);
//        gs = flame.getHistogram().getBlue(1);
//        bs = flame.getHistogram().getGreen(1);
//        int ms2 = (rs + bs + gs);
//        ms2 *= 255;
////        }
//        return 0;
//    }

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
        double scale = reference.getWidth() / flame.getHeight();
        int getAreaReference = reference.getArea();
        double similarity = Math.min(getAreaFlame * scale * scale, getAreaReference) / Math.max(getAreaFlame * scale * scale, getAreaReference);
        return similarity;
    }

}
