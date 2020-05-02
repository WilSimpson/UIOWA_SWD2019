/**
 * Used to calculate the weight of two Simple Vertices
 *
 * @param <T> Type of vertex
 *
 * @author Wil Simpson
 */
public interface EdgeWeightEvaluator<T>
{
    /**
     * Calculates the weight of two vertices
     *
     * @param v1 a vertex in the edge
     * @param v2 other vertex in an edge
     * @return weight of the edge the two verts form
     */
    int calculateEdgeWeight(SimpleVertex<T> v1, SimpleVertex<T> v2);
}
