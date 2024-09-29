// name: Maya Harari, ID: 216441469, File: Collidable
package SpiritsAndCollidables;

import Objects.Point;
import Objects.Rectangle;
import Objects.Velocity;

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-05-05
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     * @return Type: Rectangle. The rectangle shape of the collidable.
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param hitter Type: Ball. The ball which hit the Collidable.
     * @param collisionPoint Type: Point. The collisions point.
     * @param currentVelocity Type: Point. The current velocity of the object.
     * @return Type: Velocity. The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
