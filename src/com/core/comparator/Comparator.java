package com.core.comparator;

import com.core.FlameStateType;
import com.core.object.Flame;

/**
 *
 * @author Mahdi
 */
public abstract class Comparator {

    public abstract FlameStateType compare(Flame flame, Flame reference);
    
}
