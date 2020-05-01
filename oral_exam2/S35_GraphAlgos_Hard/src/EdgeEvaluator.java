public interface EdgeEvaluator<T>
{
    boolean validEdge(Edge<T> e1);

    boolean validEdge(SimpleVertex<T> v1, SimpleVertex<T> v2);

    boolean validEdge(T t1, T t2);
}
