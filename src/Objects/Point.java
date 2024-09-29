package Objects;
/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-04-23
 */
/**
 * The class Point is for the object Point.
 * A point has an x and a y value,
 * and can measure the distance to other points,
 * and if it is equal to another point.
 */
public class Point {

    /**
     * The x value of the point.
     */
    private double x;

    /**
     * The y value of the point.
     */
    private double y;

    /**
     * In order to check accuracy.
     */
    private static final double THRESHOLD = 0.00001;

    /**
     * Constructor - Sets the x and y values of the object.
     * @param x - type: double. The X value of the point.
     * @param y - type: double. The Y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
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
     * @param a Type: double. The first double number.
     * @param b Type: double. The second double number.
     * @return boolean. Returns true if the numbers
     * - doubles are equal or if a < b, false otherwise.
     */
    public static boolean doublesInequality(double a, double b) {
        return ((a - b) < THRESHOLD) || doubleEquals(a, b);
    }
    /**
     * Calculating and returning the distance of this point to the other point.
     * @param other type: Point. The point to find its distance from
     *             the class's point.
     * @return type: double. The distance of this point to the other point
     */
    public double distance(Point other) {
        if (this.equals(other)) {
            return 0;
        }
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }


    /**
     * Checking if the points are equal.
     * @param other type: Point. The point to check if it is equal
     *             to the other point.
     * @return type: boolean. Returns true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return (Math.abs(this.x - other.getX()) < THRESHOLD
                && Math.abs(this.y - other.getY()) < THRESHOLD);
    }


    /**
     * Return the x value of this point.
     * @return Type: double. The x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return y value of this point.
     * @return Type: double. The y value of this point.
     */
    public double getY() {
        return this.y;
    }
}