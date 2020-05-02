/**
 * Used to check if two inputs form a valid edge
 *
 * @param <T> Type of vertex
 *
 * @author Wil Simpson
 */
public interface EdgeEvaluator<T>
{
    /**
     * Check if the two verts form a valid edge
     *
     * @param v1 vertex in edge
     * @param v1 other vertex in edge
     * @return true if the vertices form a valid edge
     */
    boolean validEdge(SimpleVertex<T> v1, SimpleVertex<T> v2);

    /**
     * Check if the two objects form a valid edge
     *
     * @param t1 a type
     * @param t2 another type
     * @return true if the objects form a valid edge
     */
    boolean validEdge(T t1, T t2);
}
