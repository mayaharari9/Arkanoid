package Objects;

import java.util.List;
import SpiritsAndCollidables.Ball;

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-04-23
 * A line (actually a line-segment) connects two points --
 * a start point and an end point.
 * Lines have lengths, and may intersect with other lines.
 * It can also tell if it is the same as another line segment.
 */
public class Line {
    /**
     * A start point of the line.
     */
    private Point start;
    /**
     * An end point of the line.
     */
    private Point end;


    /**
     * In order to check accuracy.
     */
    private static final double UNDEFINED_SLOPE = 0;

    /**
     * In order to check accuracy.
     */
    private static final double THRESHOLD = 0.00001;

    /**
     * Constructor - When getting a start and an ent points,
     * setting the start and end Point of the object line using them.
     * @param start Type: Point. The start point of the line.
     * @param end Type: Point. The end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * Constructor - When getting the x's and y's values of the start
     * and end points, setting the start and end Point of
     * the object line using them.
     * @param x1 - type: double. The x value of the start point of the line.
     * @param y1 - type: double. The y value of the start point of the line.
     * @param x2 - type: double. The x value of the end point of the line.
     * @param y2 - type: double. The x value of the end point of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
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
     * Return the length of the line.
     * @return type: double. The length of the line.
     */
    public double length() {
        return start.distance(end);
    }
    /**
     * Returns the middle point of the line.
     * @return type: Point. middle point of the line.
     */
    public Point middle() {
        Point middle = new Point((start.getX() + end.getX()) / 2,
                (start.getY() + end.getY()) / 2);
        return middle;
    }


    /**
     * Returns the start point of the line.
     * @return type: Point. The start point of the line.
     */
    public Point start() {
       double x = this.start.getX();
       double y = this.start.getY();
       return new Point(x, y);
    }
    /**
     * Returns the end point of the line.
     * @return type: Point. The end point of the line.
     */
    public Point end() {
        double x = this.end.getX();
        double y = this.end.getY();
        return new Point(x, y);
    }


    /**
     * Checks if the lines intersect.
     * Returns true if the lines intersect, false otherwise
     * @param other Type: line. The other line.
     * @return Type: boolean. Returns true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        //If the lines have one intersection point.
        if (this.intersectionWith(other) != null) {
            return true;
        }
        //If the lines have an infinite amount of intersection points.
        if (linesHaveInfiniteIntersections(other)) {
            return true;
        }
        //Otherwise, the lines are not intersecting.
        return false;
    }


    /**
     * Finds the slope of a line and returns it.
     * @return - Type: double. Returns the slope of the line.
     * If the slope of the line is not defined returning "UNDEFINED_SLOPE".
     */
    public double getSlope() {
        //If the slope of the line is not defined (also if the line is a point).
        if (Math.abs(this.start.getX() - this.end.getX()) < THRESHOLD) {
            return UNDEFINED_SLOPE;
        }

        //The slope of the line calculation.
        return ((this.end.getY() - this.start.getY())
                / (this.end.getX() - this.start.getX()));
    }


    /**
     * Checks if the point is on the line.
     * Returns true if the point is on the line and false otherwise.
     * @param point - Type: Point. The point to check if is on the line.
     * @return - Type: boolean. Returns true if the point is on the line and false otherwise.
     */
    public boolean isPointOnLine(Point point) {

        //If the line has an undefined slope.
        double lowY = Math.min(this.start.getY(), this.end.getY());
        double highY = Math.max(this.start.getY(), this.end.getY());
        if (this.isSlopeUndefined()) {
            if (doubleEquals(point.getX(), this.start.getX())
                    && (lowY - THRESHOLD) <= point.getY()
                    && (highY + THRESHOLD) >= point.getY()) {
                return true;
            }
            return false;
        }

        //If the line is actually a point (its length is 0).
        if (this.length() == 0) {
            if (point.equals(this.start)) {
                return true;
            }
            return false;
        }

        //Getting the slope of the line which is defined.
        double slope = this.getSlope();
        //b is the y-intercept of the line.
        double b = this.start.getY() - this.getSlope() * this.start.getX();

        //If the point and the line are on the same line.
        double leftX = Math.min(this.start.getX(), this.end.getX());
        double rightX = Math.max(this.start.getX(), this.end.getX());
        if (doubleEquals(point.getY(), slope * point.getX() + b)) {
            //If the point is actually on the line and not only on the continuation of this line.
            if ((leftX - THRESHOLD) <= point.getX()
                    && (rightX + THRESHOLD) >= point.getX()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the line has an undefined slope.
     * @return boolean. Returns true if the line has an undefined slope, and false otherwise.
     */
    public boolean isSlopeUndefined() {
        return doubleEquals(this.end.getX(), this.start.getX());
    }

    /**
     * Checks if the lines have the same slop, and false otherwise.
     * @param other Type: Line. The other line.
     * @return Type: boolean. Returns true if the lines have the same slop, and false otherwise.
     */
    public boolean linesHaveTheSameSlope(Line other) {
        if (Math.abs(this.getSlope() - other.getSlope()) < THRESHOLD) {

            //If both of the lines have an undefined slope.
            if (this.isSlopeUndefined() && other.isSlopeUndefined()) {
                return true;
            }

            //If only one of the lines' slope is undefined -"UNDEFINED_SLOPE" (WHICH IS 0)
            // (and the other's slope is actually o).
            if (this.isSlopeUndefined() || other.isSlopeUndefined()) {
                return false;
            }

            //The lines have the same slope, and it is defined.
            return true;
        }

        return false;
    }

    /**
     * Checks if the lines are on the same other line, and false otherwise.
     * @param other Type: Line. The other line.
     * @return Type: boolean. Returns true if the lines have the same slop, and false otherwise.
     */
    public boolean linesOnTheSameLine(Line other) {
        //If the lines have the same slop.
        if (linesHaveTheSameSlope(other)) {

            //If the lines have an undefined slope.
            if (this.isSlopeUndefined()) {
                return doubleEquals(this.start.getX(), other.start.getX());
            }

            //The lines' slope is defined.
            //b is the y-intercept of the line.
            double b1 = this.start.getY() - this.getSlope() * this.start.getX();
            double b2 = other.start.getY() - other.getSlope() * other.start.getX();
            //If lines have the same slope and "b" then they are on one other line.
            if (doubleEquals(b1, b2)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Checks if the lines have an infinite amount of intersection points.
     * @param other Type: Line. The other line.
     * @return Type: boolean. Returns true if the lines have an infinite amount
     * of intersection points, and false otherwise.
     */
    public boolean linesHaveInfiniteIntersections(Line other) {
        //If one of the lines is actually a point (its length is 0).
        if (this.length() == 0) {
            return false;
        }
        if (other.length() == 0) {
            return false;
        }

        //Checking whether lines have an infinite amount of intersection points.
        //If the lines are on the same line.
        if (linesOnTheSameLine(other)) {
            //If the start of the one line is on the other but not the edges of the other,
            // Then the lines have infinite intersection points.
            if ((this.isPointOnLine(other.start) && (!other.start.equals(this.start) && !other.start.equals(this.end)))
                    || (this.isPointOnLine(other.end) && (!other.end.equals(this.start)
                    && !other.end.equals(this.end)))
                    || (other.isPointOnLine(this.start) && (!this.start.equals(other.start)
                    && !this.start.equals(other.end)))
                    || (other.isPointOnLine(this.end) && (!this.end.equals(other.start)
                    && !this.end.equals(other.end)))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     * @param other Type: Line. The other line.
     * @return Type: Point. Returns the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {

        //If one of the lines is actually a point (its length is 0).
        if (this.length() == 0) {
            if (other.isPointOnLine(this.start)) {
                return new Point(this.start.getX(), this.start.getY());
            }
            return null;
        }
        if (other.length() == 0) {
            if (this.isPointOnLine(other.start)) {
                return new Point(other.start.getX(), other.start.getY());
            }
            return null;
        }

        //Checking whether lines have an infinite amount of intersection points.
        if (linesHaveInfiniteIntersections(other)) {
                return null;
        }

        //Now for sure there is one or zero points of intersection of the lines.
        //If the lines are on the same line.
        if (linesOnTheSameLine(other)) {
            //Then if there is an intersection point it is one of the ends of the lines.
            //If it is the start of this line.
            if (this.start.equals(other.start) || this.start.equals(other.end)) {
                return new Point(this.start.getX(), this.start.getY());
            }
            //If it is the end of this line.
            if (this.end.equals(other.start) || this.end.equals(other.end)) {
                return new Point(this.end.getX(), this.end.getY());
            }
            //Otherwise there is no intersection.
            return null;
        }

        //b is the y-intercept of the line.
        //b1 is b of this line, and b2 is the others'.
        double b1 = this.start.getY() - this.getSlope() * this.start.getX();
        double b2 = other.start.getY() - other.getSlope() * other.start.getX();

        double xIntersection, yIntersection;
        //If the slope of this line is undefined (which means that the other line has a defined slope).
        if (this.isSlopeUndefined()) {
            xIntersection = this.start.getX();
            yIntersection = other.getSlope() * xIntersection + b2;
        } else if (other.isSlopeUndefined()) {
            //If the slope of the other line is undefined (which means that this line has a defined slope).
            xIntersection = other.start.getX();
            yIntersection = this.getSlope() * xIntersection + b1;
        } else {
            //Both lines have defined slopes (and they are different).
            xIntersection = (b1 - b2) / (other.getSlope() - this.getSlope());
            yIntersection = this.getSlope() * xIntersection + b1;
        }

        Point intersection = new Point(xIntersection, yIntersection);

        //If the intersection point is not actually on the lines.
        //The continuation of the lines intersect, but they are not.
        if (!this.isPointOnLine(intersection) || !other.isPointOnLine(intersection)) {
            return null;
        }

        //Returning the intersection point founded.
        return intersection;

    }


    /**
     * Checks if the lines are equal.
     * @param other Type: Line. The other line.
     * @return type: boolean. Return true is the lines are equal,
     * false otherwise.
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start) || (this.start.equals(other.end)))
                && (this.end.equals(other.end)
                || (this.end.equals(other.start)))) {
            return true;
        }
        return false;
    }
    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     * @param rect Type: Rectangle. The rectangle.
     * @return Type: Point. Return the closest intersection point to the start of the line. Otherwise, null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //Getting the intersection points of the line and the rectangle.
        List<Point> intersectionPoints = rect.intersectionPoints(new Line(this.start(), this.end()));
        Point p1 = intersectionPoints.get(0);
        Point p2 = intersectionPoints.get(1);

        if (p1 == null) {
            return p2;
        }
        if (p2 == null) {
            return p1;
        }

        if (Ball.doublesInequality(p1.distance(this.start()), p2.distance(this.start()))) {
            return p1;
        }
        return p2;
    }
}
