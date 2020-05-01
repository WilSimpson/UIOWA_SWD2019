/**
 * A simple vertex is a point on a graph that has a value.
 *
 * @param <T> Type of vertex
 *
 * @author Wil Simpson
 */
public class SimpleVertex<T>
{
    /**
     * Value of the simple vertex
     */
    private T value;

    /**
     * Create a simple vertex with a given value
     *
     * @param value value of simple vertex
     */
    public SimpleVertex(T value)
    {
        this.value = value;
    }

    /**
     * Get value of the simple vertex
     *
     * @return value of vertex
     */
    public T getValue()
    {
        return value;
    }

    /**
     * Set the value of the simple vertex
     *
     * @param value new value
     */
    public void setValue(T value)
    {
        this.value = value;
    }

    /**
     * Check if two simplevertex are equal
     *
     * @param other other simple vertex to check against
     * @return
     */
    public boolean equals(SimpleVertex<T> other)
    {
        return getValue().equals(other.getValue());
    }

    /**
     * String representation of a simple vertex
     *
     * @return string representation
     */
    @Override
    public String toString()
    {
        return String.format("SV(%s)", value);
    }

}
