package Animations;
import biuoop.DrawSurface;
/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-14
 */
public interface Animation {
    /**
     * Does the logic of drawing the animation.
     * @param d The surfers to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks if the animation running should stop. if so - returns true, otherwise- false.
     * @return Returns true if the animation running should stop. Otherwise, false.
     */
    boolean shouldStop();
}
