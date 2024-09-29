package Backgrounds;

import SpiritsAndCollidables.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-14
 */
public class DirectHitBackground implements Sprite, Background {
    @Override
    public void drawOn(DrawSurface d) {
        BackgroundBase base = new BackgroundBase(Color.BLACK);
        base.drawOn(d);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 130, 40);
        d.drawCircle(400, 130, 60);
        d.drawCircle(400, 130, 80);
        d.drawLine(400, 40, 400, 220);
        d.drawLine(310, 130, 490, 130);
    }

    @Override
    public void timePassed() {
    }
    @Override
    public Color getColor() {
        return Color.BLACK;
    }
}
