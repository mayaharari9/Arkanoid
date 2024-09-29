package Backgrounds;
/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-05-05
 */
import Animations.GameLevel;
import Objects.Point;
import Objects.Rectangle;
import SpiritsAndCollidables.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The class BackgroundRectangle is the background of the game screen which is a rectangle.
 */
public class BackgroundBase implements Sprite, Background {
    /**
     * The color of the background.
     */
    private java.awt.Color color;
    /**
     * The background rectangle.
     */
    private Rectangle rect;

    /**
     * Constructor (when getting size of screen).
     * @param width The width of the background rectangle.
     * @param height The height of the background rectangle.
     * @param color The color of the background rectangle.
     */
    public BackgroundBase(int width, int height, Color color) {
        this.rect = new Rectangle(new Point(0, height), width, height);
        this.color = color;
    }
    /**
     * Constructor.
     * @param color The color of the background rectangle.
     */
    public BackgroundBase(Color color) {
        this.rect = new Rectangle(new Point(0, 600), 800, 600);
        this.color = color;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY() - (int) rect.getHeight(),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(this.color);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY() - (int) rect.getHeight(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    @Override
    public void timePassed() {
    }
    /**
     * In charge of adding the block to the game, calling the appropriate game methods.
     * @param g Type: Game. The game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }
}
