import java.util.ArrayList;
import java.util.List;

public class Vertex<T>
{
    private T value;
    private List<Vertex<T>> edges;

    public Vertex(T value)
    {
        this(value, new ArrayList<>());
    }

    public Vertex(T value, List<Vertex<T>>  edges)
    {
        this.value = value;
        this.edges = edges;
    }

    public void addEdge(Vertex<T> v)
    {
        edges.add(v);
    }

    public List<Vertex<T>> getEdges()
    {
        return edges;
    }

    public T getValue()
    {
        return value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }
}
