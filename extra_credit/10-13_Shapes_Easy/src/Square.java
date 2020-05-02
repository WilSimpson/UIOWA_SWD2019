/**
 * A simple 2D shape defined by the length of one side
 *
 * @author Wil Simpson
 */
public class Square extends TwoDimensionalShape
{
    /**
     * Length of one side for the square
     */
    public double sideLength;

    /**
     * Creates a square given one side length
     *
     * @param sideLength length of one side
     */
    public Square(double sideLength)
    {
        this.sideLength = sideLength;
    }

    /**
     * Calculates the area of the square
     *
     * @return area of the square
     */
    @Override
    public double getArea()
    {
        return sideLength * sideLength;
    }
}
