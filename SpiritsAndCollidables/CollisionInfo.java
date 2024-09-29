package SpiritsAndCollidables;

import Objects.Line;
import Objects.Point;

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-05-05
 */
public class CollisionInfo {

    /**
     * The collidable Object.
     */
    private Collidable collidableObject;

    /**
     * The trajectory of the ball ("how the ball will move
     *  without any obstacles").
     */
    private Line trajectoryOfBall;

    /**
     * Constructor - When getting the collidable object and the ball.
     * @param collidableObject Type: collidableObject. The collidable Object, that the ball might collide with.
     * @param ball Type: ball. The ball.
     */
    public CollisionInfo(Collidable collidableObject, Ball ball) {
        this.collidableObject = collidableObject;
        Point start = new Point(ball.getX(), ball.getY());
        Point end = new Point(ball.getX() + ball.getVelocity().getDx(), ball.getY() + ball.getVelocity().getDy());
        this.trajectoryOfBall = new Line(trajectoryOfBall.start(), trajectoryOfBall.end());
    }
    /**
     * Constructor - When getting the collidable object and the trajectory of ball.
     * @param collidableObject Type: collidableObject. The collidable Object, that the ball might collide with.
     * @param trajectoryOfBall Type: Line. The trajectory of ball.
     */
    public CollisionInfo(Collidable collidableObject, Line trajectoryOfBall) {
        this.collidableObject = collidableObject;
        this.trajectoryOfBall = new Line(trajectoryOfBall.start(), trajectoryOfBall.end());
    }

    /**
     * Setter- When getting the collidable object and the ball.
     * @param collidableObject Type: collidableObject. The collidable Object, that the ball might collide with.
     * @param ball Type: ball. The ball.
     */
    public void setCollisionInfo(Collidable collidableObject, Ball ball) {
        this.collidableObject = collidableObject;
        Point start = new Point(ball.getX(), ball.getY());
        Point end = new Point(ball.getX() + ball.getVelocity().getDx(), ball.getY() + ball.getVelocity().getDy());
        this.trajectoryOfBall = new Line(trajectoryOfBall.start(), trajectoryOfBall.end());
    }


    /**
     * Finds the point at which the collision occurs.
     * @return Type: point. Returns the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.trajectoryOfBall.closestIntersectionToStartOfLine(this.collidableObject.getCollisionRectangle());
    }


    /**
     * Returns the collidable object involved in the collision.
     * @return Type: point. Returns the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collidableObject;
    }

}
