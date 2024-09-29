package SpiritsAndCollidables;
/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-05-05
 */
import biuoop.DrawSurface;
/**
 * A sprite is a game object that can be drawn to the screen (and which is not just a background image).
 * Sprites can be drawn on the screen, and can be notified that time has passed
 */
public interface Sprite {
    /**
     * Draws the sprite to the screen.
     * @param d Type: DrawSurface. The surface to draw on the sprite.
     */
    void drawOn(DrawSurface d);
    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}
