/**
 * A filled form that can have an area
 *
 * @author Wil Simpson
 */
public abstract class Shape
{
    /**
     * Calculates the area of the shape
     *
     * @return area of the shape
     */
    public abstract double getArea();

    /**
     * Creates a more human readable name for the class.
     *
     * @return class name of the object
     */
    @Override
    public String toString()
    {
        return getClass().getName();
    }
}
