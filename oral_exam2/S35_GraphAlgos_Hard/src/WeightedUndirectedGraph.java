import java.util.*;

/**
 * A weighted undirected graph is an undirected graph where a weight can be assigned to all edges of the graph
 *
 * @param <T> Type of vertices in the graph
 */
public class WeightedUndirectedGraph<T> extends UndirectedGraph<T>
{
    /**
     * Evaluator used to calculate the weight of an edge
     */
    private EdgeWeightEvaluator<T> edgeWeightEvaluator;

    /**
     * Creates a new weighted undirected graph
     *
     * @param verts vertices in the graph
     * @param edgeEvaluator evaluator to check if two vertices are adjacent
     * @param edgeWeightEvaluator evaluator to calculate the weight of an edge
     */
    public WeightedUndirectedGraph(List<Vertex<T>> verts, EdgeEvaluator<T> edgeEvaluator, EdgeWeightEvaluator<T> edgeWeightEvaluator)
    {
        super(verts, edgeEvaluator);
        this.edgeWeightEvaluator = edgeWeightEvaluator;
    }

    /**
     * Gets the weight of an edge
     *
     * @param v1 a vertex in the edge
     * @param v2 other vertex in the edge
     * @return weight of the edge
     */
    public int getWeight(SimpleVertex<T> v1, SimpleVertex<T> v2)
    {
        return edgeWeightEvaluator.calculateEdgeWeight(v1, v2);
    }
}
