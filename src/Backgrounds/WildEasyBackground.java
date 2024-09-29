package Backgrounds;

import SpiritsAndCollidables.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-14
 */
public class WildEasyBackground implements Sprite, Background {
    @Override
    public void drawOn(DrawSurface d) {
        BackgroundBase base = new BackgroundBase(Color.WHITE);
        base.drawOn(d);
        d.setColor(Color.yellow);
        d.fillCircle(150, 150, 60);
        d.setColor(Color.BLACK);
        d.drawText(100, 160, "SUN :-)", 30);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public Color getColor() {
        return Color.white;
    }
}
