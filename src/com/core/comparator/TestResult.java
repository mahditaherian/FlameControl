package com.core.comparator;

import com.core.FlameStateType;
import java.awt.Color;

/**
 *
 * @author Mahdi
 */
public class TestResult {

    private FlameStateType flameState;
    private double similarity;
    private double sizeSimilarity;
    private double colorSimilarity;
    private double areaSimilarity;
    private Color overallColor;

    public TestResult() {
    }

    public void setFlameState(FlameStateType flameState) {
        this.flameState = flameState;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public void setOverallColor(Color overallColor) {
        this.overallColor = overallColor;
    }

    public FlameStateType getFlameState() {
        return flameState;
    }

    public double getSimilarity() {
        return similarity;
    }

    public Color getOverallColor() {
        return overallColor;
    }

    public double getSizeSimilarity() {
        return sizeSimilarity;
    }

    public void setSizeSimilarity(double sizeSimilarity) {
        this.sizeSimilarity = sizeSimilarity;
    }

    public double getColorSimilarity() {
        return colorSimilarity;
    }

    public void setColorSimilarity(double colorSimilarity) {
        this.colorSimilarity = colorSimilarity;
    }

    public double getAreaSimilarity() {
        return areaSimilarity;
    }

    public void setAreaSimilarity(double areaSimilarity) {
        this.areaSimilarity = areaSimilarity;
    }

}
