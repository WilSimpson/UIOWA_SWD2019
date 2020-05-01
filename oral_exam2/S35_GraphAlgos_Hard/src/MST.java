import java.util.*;

public class MST<T>
{
    private HashMap<Vertex<T>, WeightedEdge<T>> shortestEdgeTo;
    private HashMap<Vertex<T>, Integer> distanceToEdge;
    private List<Vertex<T>> searched;
    private LinkedList<Vertex<T>> q;

    public MST(WeightedUndirectedGraph<T> g)
    {
        //Set size to increase performance
        shortestEdgeTo  = new HashMap<>(g.getSize());
        distanceToEdge = new HashMap<>(g.getSize());
        searched        = new ArrayList<>(g.getSize());
        q               = new LinkedList<>();

        for(Vertex<T> v : g.getVerts())
            distanceToEdge.put(v, -1);

        for(Vertex<T> v : g.getVerts())
            prim(g, v);

    }

    public void prim(WeightedUndirectedGraph<T> g, Vertex<T> v)
    {
        distanceToEdge.put(v, 0);
        q.add(v);
        while(!q.isEmpty())
        {
            Vertex<T> v1 = q.pop();
            searched.add(v1);
            for(Vertex<T> v2 : v.getEdges())
            {
                if(!searched.contains(v2))
                {
                    int newWeight = g.getWeight(v1, v2);
                    int oldWeight = distanceToEdge.get(v2);
                    if(newWeight < oldWeight
                        || oldWeight == -1)
                    {
                        distanceToEdge.put(v2, newWeight);
                        shortestEdgeTo.put(v2, new WeightedEdge<>(v1, v2, false, newWeight));
                    }
                    if(!q.contains(v2)) q.add(v2);
                }
            }
        }
    }

    public List<WeightedEdge<T>> getEdges()
    {
        return new ArrayList<>(shortestEdgeTo.values());
    }

    public int getWeight()
    {
        int weight = 0;
        for(WeightedEdge<T> e : shortestEdgeTo.values())
        {
            weight += e.getWeight();
        }

        return weight;
    }
}
