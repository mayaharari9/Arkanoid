package SpiritsAndCollidables;
/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-05-05
 */

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A "SpriteCollection" holds a collection of sprites.
 */
public class SpriteCollection {
    /**
     * A list of the sprites of the game.
     */
    private List<Sprite> spriteObjects = new LinkedList<>();
    /**
     * Adds a sprite to the list of the sprites of the game.
     * @param s Type: Sprite. The sprite to add to the list.
     */
    public void addSprite(Sprite s) {
        this.spriteObjects.add(s);
    }

    /**
     * Removes a sprite to the list of the sprites of the game.
     * @param s Type: Sprite. The sprite to remove from the list.
     */
    public void removeSprite(Sprite s) {
        this.spriteObjects.remove(s);
    }

    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<>(this.spriteObjects);
        for (int i = 0; i < sprites.size(); i++) {
            if (sprites.get(i) != null) {
                sprites.get(i).timePassed();
            }
        }
    }

    /**
     * Call drawOn(d) on all sprites.
     * @param d
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> sprites = new ArrayList<>(this.spriteObjects);
        for (int i = 0; i < sprites.size(); i++) {
            if (sprites.get(i) != null) {
                sprites.get(i).drawOn(d);
            }
        }
    }
}