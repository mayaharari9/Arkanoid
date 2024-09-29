package SpiritsAndCollidables;

import Animations.GameLevel;
import LevelInformations.GameEnvironment;
import Objects.Line;
import Objects.Point;
import Objects.Rectangle;
import Objects.Velocity;
import biuoop.GUI;
import biuoop.DrawSurface;
// name: Maya Harari, ID: 216441469, File: Ball

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-04-23
 */
public class Ball implements Sprite {
    /**
     * In order to check accuracy.
     */
    private static final double THRESHOLD = 0.00001;
    /**
     * Number of borders of the frame (a frame has 4 sides).
     */
    private static final int NUMBER_OF_BORDERS = 4;
    /**
     * The right side of the frame .
     * Which is used for setting a starting point for the ball if it
     * is out of this "stating frame" (that is defined by the other starting frame edges)
     * or in a collidable object.
     */
    private int rightEdgeFrameForStarting = 800;

    /**
     * The high side of the frame,
     * Which is used for setting a starting point for the ball if it
     * is out of this "stating frame" (that is defined by the other starting frame edges)
     * or in a collidable object.
     */
    private int highEdgeFrameForStarting = 600;
    /**
     * The left side of the frame.
     * Which is used for setting a starting point for the ball if it
     * is out of this "stating frame" (that is defined by the other starting frame edges)
     * or in a collidable object.
     */
    private int leftEdgeFrameForStarting = 0;
    /**
     * The low side of the frame.
     * Which is used for setting a starting point for the ball if it
     * is out of this "stating frame" (that is defined by the other starting frame edges)
     * or in a collidable object.
     */
    private int lowEdgeFrameForStarting = 0;
    /**
     * The center point of the ball.
     */
    private Point center;

    /**
     * The radius of the ball.
     */
    private int size;

    /**
     * The color of the ball.
     */
    private java.awt.Color color;

    /**
     * The velocity of the ball.
     */
    private Velocity velocity = new Velocity(5, -5);

    /**
     * The game environment.
     */
    private GameEnvironment gameEnvironment;


    /**
     * Constructor - when getting the center point (as a point).
     * Sets the center point,
     * the radius and the color of the ball.
     *
     * @param center Type: Point. The center point of the ball.
     * @param size   Type: int. The radius of the ball.
     * @param color  Type: java.awt.Color. The color of the ball.
     */
    public Ball(Point center, int size, java.awt.Color color) {
        this.center = new Point(center.getX(), center.getY());
        this.size = Math.abs(size);
        this.color = color;
    }

    /**
     * Constructor - when getting the center point (as a point).
     * Sets the center point,
     * the radius and the color of the ball and the game environment.
     *
     * @param center Type: Point. The center point of the ball.
     * @param size   Type: int. The radius of the ball.
     * @param color  Type: java.awt.Color. The color of the ball.
     * @param gameEnvironment Type: GameEnvironment. The gameEnvironment of the ball.
     */
    public Ball(Point center, int size, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(center.getX(), center.getY());
        this.size = Math.abs(size);
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }


    /**
     * Constructor - when getting the x's and y's values of the center point.
     * Sets the center point,
     * the radius and the color of the ball.
     *
     * @param x     Type: double. The x's value of the center point.
     * @param y     Type: double. The y's value of the center point.
     * @param size  Type: int. The radius of the ball.
     * @param color Type: java.awt.Color. The color of the ball.
     */
    public Ball(double x, double y, int size, java.awt.Color color) {
        this.center = new Point(x, y);
        this.size = Math.abs(size);
        this.color = color;
    }

    /**
     * In charge of adding the ball to the game, calling the appropriate game methods.
     * @param g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Sets the game environment of the ball.
     * @param environment Type: GameEnvironment. The game environment of the ball.
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * Returns the x rate of the ball's center point.
     *
     * @return Type: double. The x rate of the ball's center point.
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }

    /**
     * Returns the y rate of the ball's center point.
     *
     * @return Type: double. The y rate of the ball's center point.
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }


    /**
     * Sets the frame the ball will be drawn on.
     *
     * @param left  Type: int. The left side of the frame.
     * @param right Type: int. The right side of the frame.
     * @param low   Type: int. The low side of the frame.
     * @param high  Type: int. The high side of the frame.
     */
    public void setFrame(int left, int right, int low, int high) {
        this.leftEdgeFrameForStarting = left;
        this.rightEdgeFrameForStarting = right;
        this.lowEdgeFrameForStarting = low;
        this.highEdgeFrameForStarting = high;
    }


    /**
     * Returns the radius of the ball.
     *
     * @return Type: int. The radius of the ball.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns the color of the ball.
     *
     * @return Type: java.awt.Color. The color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Returns the center point of the ball (a copy).
     *
     * @return Type: Point. Returns the center point of the ball (a copy).
     */
    public Point getCenter() {
        return new Point(this.getX(), this.getY());
    }


    @Override
    public void drawOn(DrawSurface surface) {
        GUI gui;
        int x = this.getX();
        int y = this.getY();
        int size1 = this.getSize();
        java.awt.Color color1 = this.getColor();
        surface.setColor(color1);
        surface.fillCircle(x, y, size1);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Sets the center of the ball.
     * @param x Type: double. The new x value for the center of the ball.
     * @param y Type: double. The new y value for the center of the ball.
     */
    private void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }
    /**
     * Constructor - Sets the velocity of the ball.
     *
     * @param v Type: Velocity. The velocity for the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * Constructor - Sets the velocity of the ball,
     * when getting the dx and dy values of the velocity.
     * @param dx Type: double. The dx value of this velocity.
     * @param dy Type: double. The dy value of this velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Returns the velocity og the ball.
     *
     * @return Type: Velocity. Returns the velocity og the ball.
     */
    public Velocity getVelocity() {
        return new Velocity(this.velocity.getDx(), this.velocity.getDy());
    }


    /**
     * Checks if two numbers - doubles are equal using the defined THRESHOLD.
     * @param a Type: double. The first double number.
     * @param b Type: double. The second double number.
     * @return boolean. Returns true if the numbers - doubles are equal, false otherwise.
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < THRESHOLD;
    }

    /**
     * Checks if two numbers, doubles are equal or if the first
     * is smaller than the other, using the defined THRESHOLD.
     *
     * @param a Type: double. The first double number.
     * @param b Type: double. The second double number.
     * @return boolean. Returns true if the numbers
     * - doubles are equal or if a < b, false otherwise.
     */
    public static boolean doublesInequality(double a, double b) {
        return ((a - b) < THRESHOLD) || doubleEquals(a, b);
    }


    /**
     * If the ball is outside the frame, moving it inside.
     *
     * @return Type: boolean. Returns true if the function
     * had to move the ball (enter it into the frame), false otherwise.
     */
    public boolean enterBallToFrame() {
        boolean ballWasMoved = false;
        //If the ball is outside the frame, moving it inside.
        if (this.getX() - this.size < -THRESHOLD + this.leftEdgeFrameForStarting) {
            this.center = new Point(this.leftEdgeFrameForStarting + this.getSize(), this.getY());
            ballWasMoved = true;
        }
        if (this.getX() + this.size > THRESHOLD + this.rightEdgeFrameForStarting) {
            this.center = new Point(this.rightEdgeFrameForStarting - this.getSize(), this.getY());
            ballWasMoved = true;
        }
        if (this.getY() - this.size < -THRESHOLD + this.lowEdgeFrameForStarting) {
            this.center = new Point(this.getX(), this.leftEdgeFrameForStarting + this.getSize());
            ballWasMoved = true;
        }
        if (this.getY() + this.size > THRESHOLD + this.highEdgeFrameForStarting) {
            this.center = new Point(this.getX(), this.highEdgeFrameForStarting - this.getSize());
            ballWasMoved = true;
        }
        return ballWasMoved;
    }

    /**
     * Checks if the ball is in a collidable objects of the game.
     * @param ball Type: Ball. The ball to check if is inside a collidable.
     * @return Type: boolean. Returns true if the point is in a collidable and false otherwise.
     */
    public boolean isBallInCollidable(Ball ball) {
        if (this.gameEnvironment == null || this.gameEnvironment.getCollidableObjects() == null) {
            return false;
        }
        for (int i = 0; i < this.gameEnvironment.getCollidableObjects().size(); i++) {
            Rectangle currentCollidableRect =
                    this.gameEnvironment.getCollidableObjects().get(i).getCollisionRectangle();
            if (currentCollidableRect.isBallInside(ball)) {
                return true;
            }
        }
        return false;
    }


    /**
     * If the ball (its center) is inside a collidable object, moving it to a place that is not in a collidable object.
     * The animation will look ok because if a ball is moving it can't get into a collidable object.
     * Therefore, if a ball is inside a callable object it hasn't started moving and moving its place
     * before it started moving won't heart the animation.
     * @return Type: boolean. Returns true if the function
     * had to move the ball (getting out of a collidable object), false otherwise.
     */
    public boolean moveBallOutOfCollidable() {
        if (!this.isBallInCollidable(new Ball(this.getCenter(), this.getSize(), this.getColor()))) {
            return false;
        }
        //If the ball is inside a collidable object.
        //Finding a place for the ball that is not in a collidable object.
        boolean flag = false;
        for (int i = leftEdgeFrameForStarting; i < rightEdgeFrameForStarting; i++) {
            for (int j = lowEdgeFrameForStarting; j < highEdgeFrameForStarting; j++) {
                if (!this.isBallInCollidable(new Ball(new Point(i, j), 0, this.getColor()))) {
                    this.setCenter(i, j);
                    return true;
                }
            }
        }
        //If there is no place founded for the ball.
        System.out.println("error! There is no place on the screen for a ball!\nThe program ends.");
        System.exit(1);
        return false;
    }


    /**
     * If a ball is on the edge of a collidable object the function will update its
     * velocity so its next move - place will be in the right place.
     */
    public void updateVelocityIfBallOnEdgeOfCollidable() {
        if (this.gameEnvironment == null || this.gameEnvironment.getCollidableObjects() == null) {
            return;
        }
        //If a ball is on the edge of a collidable object.
        //update its velocity so its next move - place will be in the right place.
        Collidable currentCollidable;
        for (int i = 0; i < this.gameEnvironment.getCollidableObjects().size(); i++) {
            if (gameEnvironment.getCollidableObjects().get(i) == null) {
                continue;
            }
            currentCollidable = gameEnvironment.getCollidableObjects().get(i);
            if (currentCollidable.getCollisionRectangle().isPointOnside(this.getCenter())) {
                this.setVelocity(gameEnvironment.getCollidableObjects().get(i).hit(this, this.getCenter(),
                        this.getVelocity()));
            }
        }
        //If the ball is on the borders there is priority for the velocity change according to them rather
        // than other collidable objects so the balls next move - place will be in the right place.
        for (int i = 0; i < Math.min(this.gameEnvironment.getCollidableObjects().size(), NUMBER_OF_BORDERS); i++) {
            if (gameEnvironment.getCollidableObjects().get(i) == null) {
                continue;
            }
            currentCollidable = gameEnvironment.getCollidableObjects().get(i);
            if (currentCollidable.getCollisionRectangle().isPointOnside(this.getCenter())) {
                this.setVelocity(gameEnvironment.getCollidableObjects().get(i).hit(this, this.getCenter(),
                        this.getVelocity()));
            }
        }
    }

    /**
     * Moves the ball in one step according to its speed.
     * if its speed needs to be changed,
     * (due to hitting the edge of the frame) the function
     * changes its speed of the ball accordingly.
     */
    public void moveOneStep() {

        //If the ball is outside the frame, moving it inside.
        //If the ball (its center) is inside a collidable object,
        // Moving it to a place that is not in a collidable object.
        if (this.enterBallToFrame() || this.moveBallOutOfCollidable()) {
            return;
        }
        //If a ball is on the edge of a collidable object: changing it velocity so its next move\place
        // will be in the right place (and not accidentally in a collidable object).
        this.updateVelocityIfBallOnEdgeOfCollidable();

        Point movedCenter = this.getVelocity().applyToPoint(this.center);
        Line ballTrack = new Line(this.getCenter(), movedCenter);
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(ballTrack);

        //If there is no collision.
        if (collisionInfo == null || collisionInfo.collisionPoint() == null
                || this.getCenter().equals(collisionInfo.collisionPoint())) {
            this.setCenter(movedCenter.getX(), movedCenter.getY());
            return;
        }
        //There is a collision with a collidable object.
        this.setCenter(collisionInfo.collisionPoint().getX(), collisionInfo.collisionPoint().getY());
        this.setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(),
                this.getVelocity()));
    }

    /**
     * In charge of removing the ball from the game, calling the appropriate game methods.
     * @param gameLevel Type: Game. The game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
