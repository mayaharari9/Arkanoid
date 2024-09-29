package Hitting;

import SpiritsAndCollidables.Ball;
import SpiritsAndCollidables.Block;

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-07
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit The block which is hit.
     * @param hitter The Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
