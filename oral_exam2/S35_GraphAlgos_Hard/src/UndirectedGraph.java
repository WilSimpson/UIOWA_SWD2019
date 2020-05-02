import java.util.*;

/**
 * Consists of a list of verts on where their adjacent edges are store in the vertex itself
 *
 * @param <T> Type of vertex
 *
 * @author Wil Simpson
 */
public class UndirectedGraph<T>
{
    /**
     * List of vertices in graph
     */
    private List<Vertex<T>> verts;

    /**
     * Use the edge evaluator to check every vertex in the list against each other to see if it forms an edge. If two
     * vertices form an edge then both vertices will add the other as an adjacent vertex.
     *
     * @param verts list of vertices in graph
     * @param edgeEvaluator evaluator to use to check edge validity
     */
    public UndirectedGraph(List<Vertex<T>> verts, EdgeEvaluator<T> edgeEvaluator)
    {
        this.verts = verts;

        //Create edges
        for(int i=0; i<verts.size(); i++)
        {
            for(int j=i+1; j<verts.size(); j++)
            {
                Vertex<T> vert1 = verts.get(i);
                Vertex<T> vert2 = verts.get(j);

                if((vert1 != null && vert2 != null) &&
                        edgeEvaluator.validEdge(vert1.getValue(), vert2.getValue()))
                {
                    vert1.addEdge(vert2);
                    vert2.addEdge(vert1);
                }
            }
        }
    }

    /**
     * Replaces the vertices in the graph with the given vertices
     *
     * @param verts new vertices in the graph
     */
    public void setVerts(List<Vertex<T>> verts)
    {
        this.verts = verts;
    }

    /**
     * Gets the number of vertices in the graph
     *
     * @return nubmer of vertices in the graph
     */
    public int getSize()
    {
        return verts.size();
    }

    /**
     * Finds the largest subset of adjacent vertices in the graph using a BFS algorithm
     *
     * @return largest subset of vertices in the graph
     */
    public List<Vertex<T>> findLargestSubsetBFS()
    {
        ArrayList<Vertex<T>> largestConnectedSetVerts = new ArrayList<>();
        HashMap<T, Boolean> searched = new HashMap<>();
        LinkedList<Vertex<T>> queue = new LinkedList<>();

        for(Vertex<T> startingVert : getVerts())
        {
            if(!searched.containsKey(startingVert.getValue()))
            {
                searched.put(startingVert.getValue(), true);
                queue.add(startingVert);
            }

            ArrayList<Vertex<T>> currentConnectedVerts = new ArrayList<>();

            while(!queue.isEmpty())
            {

                Vertex<T> vert = queue.poll();
                currentConnectedVerts.add(vert);
                for(Vertex<T> adjVert : vert.getEdges())
                {
                    if(!searched.containsKey(adjVert.getValue()))
                    {
                        searched.put(adjVert.getValue(), true);
                        queue.add(adjVert);
                    }
                }

                if(currentConnectedVerts.size() > largestConnectedSetVerts.size())
                    largestConnectedSetVerts = currentConnectedVerts;
            }
        }

        return largestConnectedSetVerts;
    }

    /**
     * Finds the largest subset of adjacent vertices in the graph using a DFS algorithm
     *
     * @return largest subset of vertices in the graph
     */
    public List<Vertex<T>> findLargestSubsetDFS()
    {
        ArrayList<Vertex<T>> largestConnectedSetVerts = new ArrayList<>();
        HashMap<T, Boolean> searched = new HashMap<>();

        for(Vertex<T> vert : getVerts())
        {
            ArrayList<Vertex<T>> currentConnectedVerts = new ArrayList<>();
            if(!searched.containsKey(vert.getValue()))
            {
                searched.put(vert.getValue(), true);
                findLargestSubsetDFSRecursive(vert, searched, currentConnectedVerts);

                if(currentConnectedVerts.size() > largestConnectedSetVerts.size())
                    largestConnectedSetVerts = currentConnectedVerts;
            }
        }

        return largestConnectedSetVerts;
    }

    /**
     * Recursively finds all adjacent verts to the given vert. The vert has already been searched if the searched list
     * contains the vertex
     *
     * @param vert vert to search adjacent verts
     * @param searched key of the
     * @param currentConnectedVerts list of adjacent verts
     */
    private void findLargestSubsetDFSRecursive(Vertex<T> vert, Map<T, Boolean> searched, List<Vertex<T>> currentConnectedVerts)
    {
        currentConnectedVerts.add(vert);
        searched.put(vert.getValue(), true);
        for(Vertex<T> adjVert : vert.getEdges())
        {
            if(!searched.containsKey(adjVert.getValue()))
            {
                findLargestSubsetDFSRecursive(adjVert, searched, currentConnectedVerts);
            }
        }
    }

    /**
     * Gets all vertices in the graph
     *
     * @return vertices in the graph
     */
    public List<Vertex<T>> getVerts()
    {
        return verts;
    }
}
