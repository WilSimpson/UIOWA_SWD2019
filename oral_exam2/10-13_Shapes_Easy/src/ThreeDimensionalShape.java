/**
 * A 3D is a simple shape that has volume as well as surface area in place of area
 */
public abstract class ThreeDimensionalShape extends Shape
{
    /**
     * Calculates the volume of the 3D shape
     *
     * @return volume of the 3D shape
     */
    public abstract double getVolume();

    /**
     * The same as Shape#getArea() however is a more human understandable method name
     *
     * @return surface area of the 3D shape
     */
    public double getSurfaceArea()
    {
        return getArea();
    }
}

