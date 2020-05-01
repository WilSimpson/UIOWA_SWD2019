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
     * @param t1 a type
     * @param t2 another type
     * @return true if the edges form a valid edge
     */
    boolean validEdge(T t1, T t2);
}
