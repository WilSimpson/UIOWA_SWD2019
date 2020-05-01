import java.util.*;

public class WeightedUndirectedGraph<T> extends UndirectedGraph<T>
{
    private EdgeWeightEvaluator<T> edgeWeightEvaluator;

    public WeightedUndirectedGraph(List<Vertex<T>> verts, EdgeEvaluator<T> edgeEvaluator, EdgeWeightEvaluator<T> edgeWeightEvaluator)
    {
        super(verts, edgeEvaluator);
        this.edgeWeightEvaluator = edgeWeightEvaluator;
    }

    public WeightedUndirectedGraph(List<Vertex<T>> verts, EdgeWeightEvaluator<T> edgeWeightEvaluator)
    {
        super(verts);
        this.edgeWeightEvaluator = edgeWeightEvaluator;
    }

    public Set<WeightedEdge<T>> createWeightedEdgeList()
    {
        Set<WeightedEdge<T>> edges = new HashSet<>();
        for(Vertex<T> v1 : getVerts())
        {
            for(Vertex<T> v2 : v1.getEdges())
            {
                WeightedEdge<T> newEdge = new WeightedEdge<>(v1, v2, false, getWeight(v1, v2));

                if(!containsEdge(edges, newEdge)) edges.add(newEdge);
            }
        }

        return edges;
    }

    public int getWeight(SimpleVertex<T> v1, SimpleVertex<T> v2)
    {
        return edgeWeightEvaluator.calculateEdgeWeight(v1, v2);
    }

    private boolean containsEdge(Set<WeightedEdge<T>> edges, WeightedEdge<T> edge)
    {
        for(WeightedEdge<T> currentEdge : edges)
        {
            if(currentEdge.equals(edge)) return true;
        }

        return false;
    }
}
