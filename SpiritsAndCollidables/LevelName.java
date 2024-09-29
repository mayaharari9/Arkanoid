// name: Maya Harari, ID: 216441469, File: levelName

package SpiritsAndCollidables;

import Animations.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-14
 */

public class LevelName implements Sprite {

    private static final int WIDTH_OF_FRAME = 800;
    private static final int HEIGHT_OF_FRAME = 600;
    private static final int HEIGHT_OF_SCORE_DISPLAY = 20;
    private static final int SIZE_OF_TEXT = 15;
    private static final int LEFT_UPPER_X_FOR_TEXT = 500;
    private static final int RIGHT_UPPER_X_FOR_TEXT = 18;
    private String name;

    /**
     * Constructor.
     * @param name The level name.
     */
    public LevelName(String name) {
        this.name = name;
    }
    /**
     * In charge of adding the block to the game, calling the appropriate game methods.
     * @param g Type: Game. The game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(LEFT_UPPER_X_FOR_TEXT, RIGHT_UPPER_X_FOR_TEXT, "Level Name: " + this.name,
                SIZE_OF_TEXT);
    }

    @Override
    public void timePassed() {
    }
}
