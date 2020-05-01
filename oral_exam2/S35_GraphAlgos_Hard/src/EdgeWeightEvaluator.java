public interface EdgeWeightEvaluator<T>
{
    int calculateEdgeWeight(SimpleVertex<T> v1, SimpleVertex<T> v2);
}
