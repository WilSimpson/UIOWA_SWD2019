/**
 * A simple 2D shape defined by a base length and height
 */
public class Triangle extends TwoDimensionalShape
{

    /**
     * Base length of a single side
     */
    private double baseLength;

    /**
     * Height of the triangle. Length from a corner of the triangle to the point perpendicular base of the triangle
     */
    private double height;

    /**
     * Creates a triangle given the base length and height
     *
     * @param baseLength base length of the triangle
     * @param height leight of the triangle. Length from a corner of the triangle to the point perpendicular base of the
     *               triangle
     */
    public Triangle(double baseLength, double height)
    {
        this.baseLength = baseLength;
        this.height = height;
    }

    /**
     * Calculates the area of the triangle
     *
     * @return area of the triangle
     */
    @Override
    public double getArea()
    {
        return baseLength * height / 2.0;
    }
}
