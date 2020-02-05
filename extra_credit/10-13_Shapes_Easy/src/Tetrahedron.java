/**
 * A simple 3D shape that is defined by the length of a single edge
 */
public class Tetrahedron extends ThreeDimensionalShape
{

    /**
     * Length of a single edge
     */
    private double edgeLength;

    /**
     * Creates a tetrahedron given the length of a single edge
     *
     * @param edgeLength length of a single edge
     */
    public Tetrahedron(double edgeLength)
    {
        this.edgeLength = edgeLength;
    }

    /**
     * Calculates the volume of the tetrahedron
     * Formula for calculating volume area of a tetrahedron courtesy of https://study.com/academy/lesson/volume-surface-area-of-a-tetrahedron.html
     *
     * @return volume of the tetrahedron
     */
    @Override
    public double getVolume()
    {
        return Math.sqrt(2) * Math.pow(edgeLength, 3) / 12;
    }

    /**
     * Calculates the surface area of the tetrahedron
     * Formula for calculating volume area of a tetrahedron courtesy of https://study.com/academy/lesson/volume-surface-area-of-a-tetrahedron.html
     *
     * @return surface area of the tetrahedron
     */
    @Override
    public double getArea()
    {
        return Math.sqrt(3) * edgeLength * edgeLength;
    }
}
