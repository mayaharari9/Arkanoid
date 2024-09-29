// name: Maya Harari, ID: 216441469, File: Animation
package Animations;
import Backgrounds.LoseEndScreen;
import Backgrounds.WinEndScreen;
import Objects.Counter;
import SpiritsAndCollidables.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-14
 */

public class EndAnimation implements Animation {
    private Counter score;
    private boolean running;
    private KeyboardSensor keyboard;
    private int maxPointsInGame;

    /**
     * Constructor.
     * @param ks The keyboard sensor of the gui of the game runner.
     * @param score The score of the player.
     * @param maxPointsInGame The maximum value of points the player could get in the game.
     */
    public EndAnimation(KeyboardSensor ks, Counter score, int maxPointsInGame) {
        this.keyboard = ks;
        this.score = score;
        this.maxPointsInGame = maxPointsInGame;
        this.running = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        Sprite win = new WinEndScreen(this.score);
        Sprite lose = new LoseEndScreen(this.score);

        //If the player won.
        if (this.score.getValue() == this.maxPointsInGame) {
            win.drawOn(d);
        }

        //If the player lose.
        if (this.score.getValue() < this.maxPointsInGame) {
            lose.drawOn(d);
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
