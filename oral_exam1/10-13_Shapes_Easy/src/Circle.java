/**
 * A simple 2D defined by a radius
 *
 * @author Wil Simpson
 */
public class Circle extends TwoDimensionalShape
{
    /**
     * Radius of the circle
     */
    private double radius;

    /**
     * A circle only needs the length of the radius to be calculated
     *
     * @param radius radius length for the circle
     */
    public Circle(double radius)
    {
        this.radius = radius;
    }

    /**
     * Calculates the area for the circle
     *
     * @return area of the circle
     */
    @Override
    public double getArea()
    {
        return Math.PI * radius * radius;
    }
}
