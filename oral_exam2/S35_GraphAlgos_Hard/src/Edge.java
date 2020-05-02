/**
 * An edge is formed between two vertices and can be directed
 *
 * @param <T> Type of vertices
 *
 * @author Wil Simpson
 */
public class Edge<T>
{
    /**
     * Whether the edge is directed
     */
    private boolean directed;

    /**
     * Starting point in the edge if the edge is directed. If the edge isn't directed then it is an arbitrary vertex
     * in the edge
     */
    private SimpleVertex<T> start;

    /**
     * End point in the edge if the edge is directed. If the edge isn't directed then it is the other point on the edge
     */
    private SimpleVertex<T> end;

    /**
     * Creates a new edge
     *
     * @param start start of the edge or one of the points on the edge if it isn't directed
     * @param end end of the edge or the other the points on the edge if it isn't directed
     * @param directed
     */
    public Edge(SimpleVertex<T> start, SimpleVertex<T> end, boolean directed)
    {
        this.start = start;
        this.end = end;
        this.directed = directed;
    }

    /**
     * Gets the other vertex in the edge given one of the vertices that composes an edge. If the vertex given is not
     * in the edge then null is returned
     *
     * @param vert one of the vertices in the edge
     * @return other vertex in the edge
     */
    public SimpleVertex<T> getOther(SimpleVertex<T> vert)
    {
        if(vert.equals(start))
        {
            return end;
        }
        else if(vert.equals(end))
        {
            return start;
        }

        return null;
    }

    /**
     * Gets the start of the vertex or an arbitrary one if the vertex is not directed
     *
     * @return start of the vertex or an arbitrary one if the vertex is not directed
     */
    public SimpleVertex<T> getStart()
    {
        return start;
    }

    /**
     * Gets the end of the vertex or the other arbitrary vertex is not directed
     *
     * @return end of the vertex or the other arbitrary vertex is not directed
     */
    public SimpleVertex<T> getEnd()
    {
        return end;
    }

    /**
     * Checks if given edge is equal. If the edge is directed then the edge is only equal if the two vertices are equal.
     * Otherwise if the edge is not directed then edge  is equal if the two points in the edge are equal.
     *
     * @param other edge to check if equals
     * @return true if the edges are equal
     */
    public boolean equals(Edge<T> other)
    {
        if(directed)
            return start.equals(other.getStart()) && end.equals(other.getEnd());

        return !(start.equals(other.getStart()) && end.equals(other.getEnd())
                || start.equals(other.getEnd()) && end.equals(other.getStart()));
    }

    /**
     * Creates a human readible representation of the string containing both it's edges and whether it's directed or not
     *
     * @return string representation
     */
    @Override
    public String toString()
    {
        //return "E{"+start.toString()+"}";
        return String.format("E{%s,%s,%s}", start.toString(), end.toString(), (directed ? "T" : "F"));
    }
}