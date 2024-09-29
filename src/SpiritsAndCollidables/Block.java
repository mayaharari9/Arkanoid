package SpiritsAndCollidables;

import Animations.GameLevel;
import Hitting.HitListener;
import Objects.Point;
import Objects.Rectangle;
import Objects.Velocity;
import biuoop.DrawSurface;
import Hitting.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-05-05
 */
public class Block implements Collidable, Sprite, HitNotifier {
    /**
     * The hitListeners.
     */
    private List<HitListener> hitListeners = new ArrayList<>();
    /**
     * The rectangle of the block.
     */
    private Rectangle rectangle;
    /**
     * The color of the block.
     */
    private java.awt.Color color;
    /**
     * The color of the frame of the block.
     */
    private java.awt.Color frameColor;


    /**
     * Constructor - When getting the rectangle of the block and its color.
     * @param rect Type: Rectangle. The rectangle for the block.
     * @param color Type: java.awt.Color. The color of the block.
     */
    public Block(Rectangle rect, java.awt.Color color) {
        this.rectangle = new Rectangle(rect.getUpperLeft(), rect.getWidth(), rect.getHeight());
        this.color = color;
        this.frameColor = Color.BLACK;
    }


    /**
     * Constructor - When getting the rectangle of the block and its color.
     * @param rect Type: Rectangle. The rectangle for the block.
     * @param color Type: java.awt.Color. The color of the block.
     * @param frameColor Type: java.awt.Color. The color of the frame of the block.
     */
    public Block(Rectangle rect, java.awt.Color color, java.awt.Color frameColor) {
        this.rectangle = new Rectangle(rect.getUpperLeft(), rect.getWidth(), rect.getHeight());
        this.color = color;
        this.frameColor = frameColor;
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.rectangle.getUpperLeft(), this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        //Updating the velocity according to the collision point.
        //If the collision was on the left side of the block.
        if (this.rectangle.getLeftSide().isPointOnLine(collisionPoint)) {
            newVelocity.setDx(-Math.abs(currentVelocity.getDx()));
        }
        //If the collision was on the right side of the block.
        if (this.rectangle.getRightSide().isPointOnLine(collisionPoint)) {
            newVelocity.setDx(Math.abs(currentVelocity.getDx()));
        }
        //If the collision was on the upper side of the block.
        if (this.rectangle.getUpperSide().isPointOnLine(collisionPoint)) {
            newVelocity.setDy(Math.abs(currentVelocity.getDy()));
        }
        //If the collision was on the lower side of the block.
        if (this.rectangle.getLowerSide().isPointOnLine(collisionPoint)) {
            newVelocity.setDy(-Math.abs(currentVelocity.getDy()));
        }
        //Notify about a hit event.
        this.notifyHit(hitter);
        //Now, the velocity has changed as needed.
        return newVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        Rectangle rect = this.getCollisionRectangle();
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY() - (int) rect.getHeight(),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(this.frameColor);
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
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * In charge of removing the block from the game, calling the appropriate game methods.
     * @param gameLevel Type: Game. The game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        //Making sure that hl is not already in the hitListeners.
        if (!this.hitListeners.contains(hl)) {
            this.hitListeners.add(hl);
        }
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies the hitListeners about the hit event.
     * @param hitter The ball which hit this block.
     */
    private void notifyHit(Ball hitter) {
        if (this.hitListeners == null) {
            return;
        }
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
