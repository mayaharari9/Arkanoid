package SpiritsAndCollidables;
/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-05-05
 */

import Animations.GameLevel;
import Objects.Line;
import Objects.Point;
import Objects.Rectangle;
import Objects.Velocity;
import biuoop.DrawSurface;

/**
 * The Paddle is the player in the game.
 * It is a rectangle that is controlled by the arrow keys,
 * and moves according to the player key presses.
 */
public class Paddle implements Sprite, Collidable {

    /**
     *The keyboard sensor for the paddle.
     */
    private biuoop.KeyboardSensor keyboard;
    /**
     * The rectangle of the paddle.
     */
    private Rectangle rectangle;
    /**
     * The color of the paddle.
     */
    private java.awt.Color color;
    /**
     * The horizontal value of the velocity of the paddle.
     */
    private int velocityValue = 10;

    /**
     * The minimum x value for the paddle (any point on it) so it not out of the frame of the game.
     * (20 is the width of the vertical borders).
     */
    private static final int MIN_X_FOR_PADDLE = 25;
    /**
     * The maximum x value for the paddle (any point on it) so it not out of the frame of the game.
     * (20 is the width of the vertical borders).
     */
    private static final int MAX_X_FOR_PADDLE = 800 - 25;


    /**
     * The angle of the velocity an object get after hitting the paddle on region 1.
     */
    private static final double REGION_ONE_ANGLE = 300;
    /**
     * The angle of the velocity an object get after hitting the paddle on region 2.
     */
    private static final double REGION_TWO_ANGLE = 330;
    /**
     * The angle of the velocity an object get after hitting the paddle on region 3.
     */
    private static final double REGION_THREE_ANGLE = 360;
    /**
     * The angle of the velocity an object get after hitting the paddle on region 4.
     */
    private static final double REGION_FOUR_ANGLE = 30;
    /**
     * The angle of the velocity an object get after hitting the paddle on region 5.
     */
    private static final double REGION_FIVE_ANGLE = 60;
    /**
     * Number of regions.
     */
    private static final int NUM_OF_REGIONS = 5;

    /**
     * Contractor. When getting the rectangle, the color,
     * the horizontal velocity and the keyboard sensor of the paddle.
     * @param rect Type: Rectangle. The rectangle of the paddle.
     * @param color Type: java.awt.Color. The color of the paddle.
     * @param v Type: double. The horizontal velocity of the paddle.
     * @param keyboard Type: biuoop.KeyboardSensor. The keyboard sensor of the paddle.
     */
    public Paddle(Rectangle rect, java.awt.Color color, double v, biuoop.KeyboardSensor keyboard) {
        this.rectangle = new Rectangle(rect.getUpperLeft(), rect.getWidth(), rect.getHeight());
        this.color = color;
        this.velocityValue = (int) Math.ceil(v);
        this.keyboard = keyboard;
    }

    /**
     * Moves the paddle to the left according to its horizontal value of the velocity.
     */
    public void moveLeft() {
        if (Point.doublesInequality(this.rectangle.getUpperLeft().getX() - this.velocityValue, MIN_X_FOR_PADDLE)) {
            this.rectangle = new Rectangle(new Point(MIN_X_FOR_PADDLE, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
            return;

        }
        this.rectangle = new Rectangle(new Point(this.rectangle.getUpperLeft().getX() - this.velocityValue,
                this.rectangle.getUpperLeft().getY()), this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * Moves the paddle to the right according to its horizontal value of the velocity.
     */
    public void moveRight() {
        if (Point.doublesInequality(MAX_X_FOR_PADDLE, this.rectangle.getUpperLeft().getX()
                + this.rectangle.getWidth() + this.velocityValue)) {
            this.rectangle = new Rectangle(new Point(MAX_X_FOR_PADDLE - this.rectangle.getWidth(),
                    this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
            return;

        }
        this.rectangle = new Rectangle(new Point(this.rectangle.getUpperLeft().getX() + this.velocityValue,
                this.rectangle.getUpperLeft().getY()), this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    // Sprite
    @Override
    public void timePassed() {
        //this.keyboard = this.gui.getKeyboardSensor();
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        Rectangle rect = this.getCollisionRectangle();
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY() - (int) rect.getHeight(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    // Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.rectangle.getUpperLeft(), this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        //If the collision was on one of te regions of the paddle.
        if (this.hitRegionVelocity(collisionPoint, currentVelocity) != null) {
            return this.hitRegionVelocity(collisionPoint, currentVelocity);
        }
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        //Updating the velocity according to the collision point.
        //If the collision was on the left side of the paddle.
        if (this.rectangle.getLeftSide().isPointOnLine(collisionPoint)) {
            newVelocity.setDx(-Math.abs(currentVelocity.getDx()));
        }
        //If the collision was on the right side of the paddle.
        if (this.rectangle.getRightSide().isPointOnLine(collisionPoint)) {
            newVelocity.setDx(Math.abs(currentVelocity.getDx()));
        }
        //If the collision was on the upper side of the paddle.
        if (this.rectangle.getUpperSide().isPointOnLine(collisionPoint)) {
            newVelocity.setDy(Math.abs(currentVelocity.getDy()));
        }
        //If the collision was on the lower side of the paddle.
        if (this.rectangle.getLowerSide().isPointOnLine(collisionPoint)) {
            newVelocity.setDy(-Math.abs(currentVelocity.getDy()));
        }
        //Now, the velocity has changed as needed.
        return newVelocity;
    }

    /**
     * Creates an array of the region lines of the paddle.
     * @return Type: Line[]. The  array of the region lines of the paddle.
     */
    Line[] getRegionLines() {
        Line[] lines = new Line[NUM_OF_REGIONS];
        double yForLines = this.rectangle.getUpperLeft().getY() - this.rectangle.getHeight();
        double leftXOfLeftRegion = this.rectangle.getUpperLeft().getX();
        double lenghthOfRegions = this.rectangle.getWidth() / 5;
        for (int i = 0; i < NUM_OF_REGIONS; i++) {
            lines[i] = new Line(leftXOfLeftRegion + i * lenghthOfRegions, yForLines,
                    leftXOfLeftRegion + (i + 1) * lenghthOfRegions, yForLines);
        }
        return lines;
    }

    /**
     * Creates the appropriate velocity for the object (ball) which hit the paddle,
     * if the collision was on one of te regions of the paddle.
     * @param collisionPoint Type: Point. The collision point (to check if is in the region).
     * @param currentVelocity Type: Velocity. The current velocity of the object (ball) which hit the paddle.
     * @return Type: Velocity. The new velocity if the collection was in one of the regions of the paddle,
     * null otherwise.
     */
    Velocity hitRegionVelocity(Point collisionPoint, Velocity currentVelocity) {
        //If the collision was on the left side of the block - one of the regions.
        if (!this.rectangle.getLowerSide().isPointOnLine(collisionPoint)) {
            return null;
        }
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        Line[] regionLines = this.getRegionLines();
        //region one.
        if (regionLines[0].isPointOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(REGION_ONE_ANGLE, speed);
        }
        //region two.
        if (regionLines[1].isPointOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(REGION_TWO_ANGLE, speed);
        }
        //region three.
        if (regionLines[2].isPointOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(REGION_THREE_ANGLE, speed);
        }
        //region four.
        if (regionLines[3].isPointOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(REGION_FOUR_ANGLE, speed);
        }
        //region five.
        if (regionLines[4].isPointOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(REGION_FIVE_ANGLE, speed);
        }
        return null;
    }

    /**
     * Checks if the given point is in the region k (region) of the paddle (on the region line).
     * @param point Type: Point. The point to check if is in the region.
     * @param region Type: int. The number of the region.
     * @return Type: boolean. Returns true if the point is in the region, and false otherwise.
     */
    boolean isPointInKRegion(Point point, int region) {
        Line[] regionLines = this.getRegionLines();
        return regionLines[region].isPointOnLine(point);
    }


    /**
     * Adds this paddle to the game.
     * @param g Type: Game. The game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

}