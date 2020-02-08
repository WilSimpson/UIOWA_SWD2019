/**
 * Creates a baseline for a circle. The constructor requires no parameters and creates a random radius within
 * the margins defined by the MIN_RADIUS and the MAX_RADIUS. Other useful methods are created to easily calculate the
 * properties of the circle given the randomly generated radius.
 *
 * @author Wil Simpson
 */
public class MyCircle {
    /**
     * The minimum radius the circle may be
     */
    public static final int MIN_RADIUS = 100;

    /**
     * The maximum radius the circle may be
     */
    public static final int MAX_RADIUS = 500;

    /**
     * The actual radius of the circle
     */
    private int radius;

    /**
     * Constructs the MyCircle object with no params. Randomly generates the circle inclusively between the MIN_RADIUS
     * and the MAX_RADIUS.
     */
    public MyCircle() {
        this.radius = (int) Math.round(Math.random()*(MAX_RADIUS-MIN_RADIUS)) + MIN_RADIUS;
    }

    /**
     * Returns the radius of the circle object
     *
     * @return the radius of the circle
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Calculates the diameter of the circle following the equation: 2 * radius
     *
     * @return the diameter of the circle
     */
    public int getDiameter() {
        return 2 * radius;
    }

    /**
     * Calculates the area of the circle following the equation: pi * radius^2
     *
     * @return the area of the circle
     */
    public double getArea() {
        return Math.PI * radius * radius;
    }

    /**
     * Calculates the circumference of the circle
     *
     * @return the circumference of the circle
     */
    public double getCircumference() {
        return Math.PI * 2 * radius;
    }
}
