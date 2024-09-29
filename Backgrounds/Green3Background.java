// name: Maya Harari, ID: 216441469, File: Green3Background
package Backgrounds;

import SpiritsAndCollidables.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-14
 */
public class Green3Background implements Sprite, Background {
    @Override
    public void drawOn(DrawSurface d) {
        BackgroundBase base = new BackgroundBase(new Color(255, 245, 208));
        base.drawOn(d);
    }

    @Override
    public void timePassed() {
    }
    @Override
    public Color getColor() {
        return Color.GREEN;
    }
}
