package com.core.comparator;

import com.core.object.Flame;

/**
 *
 * @author Mahdi
 */
public abstract class Comparator {

    public abstract TestResult compare(Flame flame, Flame reference);
    
}
