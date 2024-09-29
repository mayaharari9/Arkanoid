// name: Maya Harari, ID: 216441469, File: LoseEndScreen
package Backgrounds;

import Objects.Counter;
import SpiritsAndCollidables.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-14
 */
public class LoseEndScreen implements Sprite, Background {
    private Counter score;

    /**
     * Constructor.
     * @param score The score of the player.
     */
    public LoseEndScreen(Counter score) {
        this.score = score;
    }

    @Override
    public Color getColor() {
        return Color.white;
    }

    @Override
    public void drawOn(DrawSurface d) {
        //Backgrounds rectangles
        d.setColor(new Color(250, 65, 65));
        d.fillRectangle(50, 550 - 500, 700, 500);
        d.setColor(new Color(255, 204, 48));
        d.fillRectangle(100, 500 - 400, 600, 400);
        //Outline of massage
        String massage = "Game Over.";
        d.setColor(new Color(63, 56, 53));
        d.drawText(190, 200, massage, 55);
        massage = "Your score is " + this.score.getValue();
        d.setColor(new Color(63, 56, 53));
        d.drawText(195, 300, massage, 50);
        //massage
        massage = "Game Over.";
        d.setColor(new Color(241, 88, 32));
        d.drawText(195, 200, massage, 55);
        massage = "Your score is " + this.score.getValue();
        d.setColor(new Color(241, 88, 32));
        d.drawText(200, 300, massage, 50);
    }

    @Override
    public void timePassed() {
    }
}
