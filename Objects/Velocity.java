package Objects;
// name: Maya Harari, ID: 216441469, File: Velocity

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-04-23
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    /**
     * Pi radian in degree.
     */
    private static final double PI_RAD_IN_DEGREE = 180;

    /**
     * The change in position on the `x`.
     */
    private double dx = 0;
    /**
     * The change in position on the `y`.
     */
    private double dy = 0;



    /**
     * Constructor - Sets the dx and dy values of the object.
     * @param dx Type: double. The change in position on the `x`.
     * @param dy Type: double. The change in position on the `y`.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Return the dx value of this velocity.
     * @return Type: double. The dx value of this velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Return dy value of this velocity.
     * @return Type: double. The dy value of this velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Sets the dx value of the velocity.
     * @param dx Type: double. The new dx value for the velocity.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets the dy value of the velocity.
     * @param dy Type: double. The new dy value for the velocity.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return
     * a new point with position (x+dx, y+dy).
     * @param p Type: point. The point to update position.
     * @return Type: point. The new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Creates dx and dy accordingly when receiving
     * the direction of the ball's progress in the angle
     * and speed of the ball and returns a velocity object
     * defined with those dx and dy created.
     * @param angle Type: double. The direction of the change in position.
     * @param speed Type: double. The speed of the ball (units of progress).
     * @return Type: Velocity. A velocity object defined with those dx and dy created.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleR = (angle / PI_RAD_IN_DEGREE) * Math.PI;
        double dx = Math.sin(angleR) * speed;
        //Assuming up is angle 0 (as we were told), since the y's axis
        // is reversed we will define dy to be -dy
        // (reverse of how it comes out if the y's axis was not reversed).
        double dy = -Math.cos(angleR) * speed;
        return new Velocity(dx, dy);
    }
}