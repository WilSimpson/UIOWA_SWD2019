import java.util.ArrayList;
import java.util.List;

public class Vertex<T> extends SimpleVertex<T> implements Comparable<Vertex<T>>
{
    private List<Vertex<T>> edges;

    public Vertex(T value, boolean directed)
    {
        this(value, new ArrayList<>());
    }

    public Vertex(T value, List<Vertex<T>>  edges)
    {
        super(value);
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

    public List<WeightedEdge<T>> createWeightedEdges(EdgeWeightEvaluator<T> evaluator)
    {
        List<WeightedEdge<T>> newEdges = new ArrayList<>();
        for(Vertex<T> vert : edges)
        {
            newEdges.add(new WeightedEdge<T>(this, vert, false, evaluator.calculateEdgeWeight(this, vert)));
        }

        return newEdges;
    }

    @Override
    public String toString()
    {
        return super.toString().substring(1);
    }

    public String toExpandedString()
    {
        String old = super.toString();
        //old = old.substring(1, old.length()-1);
        //return old+edges+"}";
        return old.substring(1, old.length()-1)+","+edges+"}";
    }

    @Override
    public int compareTo(Vertex<T> tVertex)
    {
        return 0;
    }
}
