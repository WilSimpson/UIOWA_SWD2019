/**
 * A simple 3D shape defined by the length of a single side
 *
 * @author Wil Simpson
 */
public class Cube extends ThreeDimensionalShape
{
    /**
     * Length of a single side
     */
    private double sideLength;

    /**
     * A cube is created by giving the length of a single side
     *
     * @param sideLength side length of the cube
     */
    public Cube(double sideLength)
    {
        this.sideLength = sideLength;
    }

    /**
     * Calculates the volume of the cube
     *
     * @return volume of the cube
     */
    @Override
    public double getVolume()
    {
        return Math.pow(sideLength, 3);
    }

    /**
     * Calculates the area of the cube
     *
     * @return area of the cube
     */
    @Override
    public double getArea()
    {
        return sideLength * sideLength * 6;
    }
}
