// name: Maya Harari, ID: 216441469, File: ScoreIndicator
package Score;

import Animations.GameLevel;
import Objects.Counter;
import SpiritsAndCollidables.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-07
 * Is in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {
    private static final int WIDTH_OF_FRAME = 800;
    private static final int HEIGHT_OF_FRAME = 600;
    private static final int HEIGHT_OF_SCORE_DISPLAY = 20;
    private static final int SIZE_OF_TEXT = 15;
    private static final int LEFT_UPPER_X_FOR_TEXT = 380;
    private static final int RIGHT_UPPER_X_FOR_TEXT = 18;
    private Counter currentScore;
    private java.awt.Color color;


    /**
     * Constructor.
     * @param scoreCounter A counter of the score in the game.
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.currentScore = scoreCounter;
        this.color = Color.LIGHT_GRAY;
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
        d.setColor(this.color);
        d.fillRectangle(0, 0,
                WIDTH_OF_FRAME, HEIGHT_OF_SCORE_DISPLAY);
        d.setColor(Color.black);
        d.drawText(LEFT_UPPER_X_FOR_TEXT, RIGHT_UPPER_X_FOR_TEXT, "Score: " + this.currentScore.getValue(),
                SIZE_OF_TEXT);
    }

    @Override
    public void timePassed() {
    }
}
