/**
 * An edge with a weight associated with it
 *
 * @param <T> Type of vertices in edge
 *
 * @author Wil Simpson
 */
public class WeightedEdge<T> extends Edge<T> implements Comparable<WeightedEdge<T>>
{
    /**
     * Weight of the edge
     */
    private int weight;

    /**
     * Creates a new weighted edge
     *
     * @param v1 start of the edge or one of the points on the edge if it isn't directed
     * @param v2 end of the edge or the other the points on the edge if it isn't directed
     * @param directed whether the edge is directed or not
     * @param weight weight of the edge
     */
    public WeightedEdge(SimpleVertex<T> v1, SimpleVertex<T> v2, boolean directed, int weight)
    {
        super(v1, v2, directed);

        this.weight = weight;
    }

    /**
     * Gets the weight of an edge
     *
     * @return weight of the edge
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     * Checks if weighted edges is equal. The weighted edge is equal if the edge is equal and the weight is equal.
     *
     * @param other other weight to check
     * @return true if the weighted edges are equal
     */
    public boolean equals(WeightedEdge<T> other)
    {
        if(super.equals(other)) return false;

        return weight == other.getWeight();
    }

    /**
     * Compares the weight of the edges
     *
     * @param other
     * @return the value 0 if this weight is equal to the other; a value less than 0 if this weight is numerically less
     * than the other; and a value greater than 0 if this weight is numerically greater than the other
     */
    @Override
    public int compareTo(WeightedEdge<T> other)
    {
        return Integer.compare(this.weight, other.weight);
    }

    /**
     * Creates a human representation of the weighted edge containing the edge's properties
     *
     * @return string representation
     */
    @Override
    public String toString()
    {
        String old = "W"+super.toString();
        //return old;
        return String.format(old.substring(0,old.length()-1)+",%d}", weight);
    }
}
