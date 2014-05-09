package com.core.processor;

import com.core.comparator.Comparator;
import com.core.object.StandardPicture;
import com.core.provider.ImageProvider;

/**
 *
 * @author Mahdi
 */
public class Criteria {
    private Comparator comparator;
    private int samplingCount;
    private int samplingDelay;
    private StandardPicture reference;
    private ImageProvider provider;

    public Criteria(Comparator comparator, int samplingCount, int samplingDelay, StandardPicture reference, ImageProvider provider) {
        this.comparator = comparator;
        this.samplingCount = samplingCount;
        this.samplingDelay = samplingDelay;
        this.reference = reference;
        this.provider = provider;
    }

    public Comparator getComparator() {
        return comparator;
    }

    public int getSamplingCount() {
        return samplingCount;
    }

    public int getSamplingDelay() {
        return samplingDelay;
    }

    public StandardPicture getReference() {
        return reference;
    }

    public ImageProvider getProvider() {
        return provider;
    }
    
    
    
}
