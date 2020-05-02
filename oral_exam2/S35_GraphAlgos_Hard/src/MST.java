import java.util.*;
/**
 * A Minimum Spanning Tree (MST) can be created from any fully connected undirected weighted graph. The MST is a subset
 * of the edges in such graph that connects all the vertices together, without any cycles and with the minimum possible
 * total edge weight.
 *
 * @param <T> Type of vertices
 *
 * @author Wil Simpson
 */
public class MST<T>
{
    /**
     * All weighted edges in the MST where the key is a unique element that will create a new edge
     */
    private HashMap<Vertex<T>, WeightedEdge<T>> mst;

    /**
     * Creates a MST using Prim's algorithm from the given graph
     *
     * @param graph graph to create mst from
     */
    public MST(WeightedUndirectedGraph<T> graph)
    {
        //Set size to increase performance
        mst = new HashMap<>(graph.getSize());

        //Current lightest edge
        HashMap<Vertex<T>, Integer> vertexWeight = new HashMap<>(graph.getSize());

        //Set containing all vertices in the MST
        Set<Vertex<T>> inMST = new HashSet<>(graph.getSize());

        //Priority Queue containing vertices that may need to be added to the MST ordered by their current lightest edge
        // to a vertex inside the minimum spanning tree
        PriorityQueue<Vertex<T>> pq = new PriorityQueue<>(Comparator.comparingInt(vertexWeight::get));

        for(Vertex<T> v : graph.getVerts())
        {
            vertexWeight.put(v, -1);
        }

        //Start with a random point on the graph
        pq.add(graph.getVerts().get(0));

        //Loop until no more points can be added to the graph
        while(!pq.isEmpty())
        {
            //Check if the vertex is in the MST, if it isn't then find the lightest edge and add it to the MST
            Vertex<T> v1 = pq.poll();
            if(!inMST.contains(v1))
            {
                inMST.add(v1);
                for(Vertex<T> v2 : v1.getEdges())
                {
                    if(!inMST.contains(v2))
                    {
                        //Checks if the current edge is the lightest
                        int newWeight = graph.getWeight(v1, v2);
                        int oldWeight = vertexWeight.get(v2);
                        if(newWeight < oldWeight
                                || oldWeight == -1)
                        {
                            vertexWeight.put(v2, newWeight);
                            mst.put(v2, new WeightedEdge<>(v1, v2, false, newWeight));
                        }

                        //Remove the vertex if it's already in the pq so it's position can be recalculated with it's
                        //newly found weight value
                        pq.remove(v2);
                        pq.add(v2);
                    }
                }
            }
        }
    }

    /**
     * Creates a new list containing the edges in the MST
     *
     * @return a new list containing the edges in the MST
     */
    public List<WeightedEdge<T>> getEdges()
    {
        return new ArrayList<>(mst.values());
    }

    /**
     * Calculates the weight of every edge in the MST
     *
     * @return total weight of the MST
     */
    public int getWeight()
    {
        Set<WeightedEdge<T>> mst = new HashSet<>(this.mst.values());
        int weight = 0;
        for(WeightedEdge<T> e : mst)
        {
            weight += e.getWeight();
        }

        return weight;
    }
}
