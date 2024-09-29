package Objects;

import SpiritsAndCollidables.Ball;

import java.util.ArrayList;
/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-05-05
 */
public class Rectangle {

    /**
     * The upper left point of the rectangle.
     */
    private Point upperLeft;
    /**
     * The width of the rectangle.
     */
    private double width;

    /**
     * The height of the rectangle.
     */
    private double height;
    /**
     * In order to check accuracy.
     */
    private static final double THRESHOLD = 0.00001;


    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft Type: Point. The upper left point for the rectangle.
     * @param width Type: double. The width of the rectangle.
     * @param height Type: double. The height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
    }


    /**
     * Returns the y value of the upper left point of the rectangle.
     * @return Type: double. The  y value of the upper left point of the rectangle.
     */
    private double getUpperLeftY() {
        return this.upperLeft.getY();
    }

    /**
     * Returns the x value of the upper left point of the rectangle.
     * @return Type: double. The  x value of the upper left point of the rectangle.
     */
    private double getUpperLeftX() {
        return this.upperLeft.getX();
    }

    /**
     * Return the width of the rectangle.
     * @return Type: double. Return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     * @return Type: double. Return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     * @return Type: Point. The upper left point for the rectangle (a copy).
     */
    public Point getUpperLeft() {
        return new Point(this.getUpperLeftX(), this.getUpperLeftY());
    }

    /**
     * Creates line of the left side of the rectangle and returns it.
     * @return Type: Line. The line of the left side of the rectangle.
     */
    public Line getLeftSide() {
        return new Line(this.getUpperLeftX(), this.getUpperLeftY(),
                this.getUpperLeftX(), this.getUpperLeftY() - this.height);
    }
    /**
     * Creates line of the right side of the rectangle and returns it.
     * @return Type: Line. The line of the right side of the rectangle.
     */
    public Line getRightSide() {
        return new Line(this.getUpperLeftX() + this.width, this.getUpperLeftY(),
                this.getUpperLeftX() + this.width, this.getUpperLeftY() - this.height);
    }
    /**
     * Creates line of the upper side of the rectangle and returns it.
     * @return Type: Line. The line of the upper side of the rectangle.
     */
    public Line getUpperSide() {
        return new Line(this.getUpperLeftX(), this.getUpperLeftY(),
                this.getUpperLeftX() + this.width, this.getUpperLeftY());
    }
    /**
     * Creates line of the lower side of the rectangle and returns it.
     * @return Type: Line. The line of the lower side of the rectangle.
     */
    public Line getLowerSide() {
        return new Line(this.getUpperLeftX(), this.getUpperLeftY() - this.height,
                this.getUpperLeftX() + this.width, this.getUpperLeftY() - this.height);
    }

    /**
     * Checks if the ball is in the rectangle.
     * @param ball Type: Ball. The ball to check if is in the rectangle.
     * @return Type: boolean. True if the point is in the rectangle and false otherwise.
     */
    public boolean isBallInside(Ball ball) {
        if (Point.doublesInequality(ball.getCenter().getX() + ball.getSize(), this.getUpperLeftX() + this.width)
                && Point.doublesInequality(this.getUpperLeftX(), ball.getCenter().getX() - ball.getSize())
                && Point.doublesInequality(ball.getCenter().getY() + ball.getSize(), this.getUpperLeftY())
                && Point.doublesInequality(this.getUpperLeftY() - this.height,
                ball.getCenter().getY() - ball.getSize())) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the point is on one of the sided of the rectangle.
     * @param point Type: Point. The point to check if is on one of the sided of the rectangle.
     * @return Type: boolean. True if the point is in the rectangle and false otherwise.
     */
    public boolean isPointOnside(Point point) {
        if (this.getLeftSide().isPointOnLine(point) || this.getRightSide().isPointOnLine(point)
                || this.getLowerSide().isPointOnLine(point) || this.getUpperSide().isPointOnLine(point)) {
            return true;
        }
        return false;
    }
    /**
     * Finds a (possibly empty) List of intersection points
     * with the specified line.
     * @param line Type: Line. The line which intersect with the rectangle.
     * @return Type: List< Point >. Returns a (possibly empty) List of
     * intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line leftSide = this.getLeftSide();

        Line rightSide = this.getRightSide();

        Line upperSide = this.getUpperSide();

        Line lowerSide = this.getLowerSide();

        //Initializing the array of the inserctuion points with the sides of the rectangle.
        Point[] intersectionArray = new Point[4];
        for (int i = 0; i < intersectionArray.length; i++) {
            intersectionArray[i] = null;
        }

        //Finding the intersection points of the rectangle with the specified line.
        intersectionArray[0] = leftSide.intersectionWith(line);
        intersectionArray[1] = rightSide.intersectionWith(line);
        intersectionArray[2] = upperSide.intersectionWith(line);
        intersectionArray[3] = lowerSide.intersectionWith(line);

        //A rectangle and a line can have only up to 2 intersection points.
        //Finding the intersection points.
        Point p1 = null;
        Point p2 = null;
        for (int i = 0; i < intersectionArray.length; i++) {
            if (intersectionArray[i] != null) {
                if (p1 == null) {
                    p1 = intersectionArray[i];
                    continue;
                }
                if (!intersectionArray[i].equals(p1)) {
                    p2 = intersectionArray[i];
                }
            }
        }
        java.util.List<Point> list = new ArrayList<Point>();
        list.add(p1);
        list.add(p2);
        return list;
    }

}
