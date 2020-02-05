/**
 * A simple 3D shape defined by the length of the radius
 */
public class Sphere extends ThreeDimensionalShape
{
    /**
     * Radius of the sphere
     */
    private double radius;

    /**
     * Creates a sphere given a radius length
     *
     * @param radius length of the radius
     */
    public Sphere(double radius)
    {
        this.radius = radius;
    }

    /**
     * Calculates the volume of the sphere
     * Formula for calculating the volume courtesy of <a href="https://www.mathsisfun.com/geometry/sphere-volume-area.html">mathsisfun.com</a>
     *
     * @return volume of the sphere
     */
    @Override
    public double getVolume()
    {
        return (4/3.0) * Math.PI * Math.pow(radius, 3);
    }

    /**
     * Calculates the surface area of the sphere
     * Formula for calculating the area of a cube courtesy of <a href="https://www.mathsisfun.com/geometry/sphere-volume-area.html>mathsisfun.com</a>
     *
     * @return surface area of the sphere
     */
    @Override
    public double getArea()
    {
        return 4 * Math.PI * radius * radius;
    }
}
