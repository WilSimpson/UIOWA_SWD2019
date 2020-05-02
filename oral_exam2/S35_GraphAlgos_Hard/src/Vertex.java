import java.util.ArrayList;
import java.util.List;

/**
 * A vertex that stores all vertices adjacent to itself
 *
 * @param <T> Type of vertex
 */
public class Vertex<T> extends SimpleVertex<T>
{
    /**
     * Adjacent vertices
     */
    private List<Vertex<T>> edges = new ArrayList<>();

    /**
     * Creates a new vertex given a value
     *
     * @param value
     */
    public Vertex(T value)
    {
        super(value);
    }

    /**
     * Links a vertex to the current vertex that forms an edge
     *
     * @param v adjacent vertex
     */
    public void addEdge(Vertex<T> v)
    {
        edges.add(v);
    }

    /**
     * Gets all adjacent vertices
     *
     * @return adjacent vertices
     */
    public List<Vertex<T>> getEdges()
    {
        return edges;
    }
}
