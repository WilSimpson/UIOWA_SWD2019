import java.util.*;
import java.util.stream.Collectors;

public class UndirectedGraph<T>
{
    private List<Vertex<T>> verts;

    public UndirectedGraph(List<Vertex<T>> verts)
    {
        this.verts = verts;
    }

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

    public void setVerts(List<Vertex<T>> verts)
    {
        this.verts = verts;
    }

    public int getSize()
    {
        return verts.size();
    }

    public Vertex<T> getVertex(T t)
    {
        for(Vertex<T> vert : verts)
        {
            if(vert.getValue().equals(t))
            {
                return vert;
            }
        }

        return null;
    }

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

    public List<Edge<T>> createEdgeList()
    {
        List<Edge<T>> edges = new ArrayList<>();
        for(Vertex<T> v1 : verts)
        {
            for(Vertex<T> v2 : v1.getEdges())
            {
               edges.add(new Edge<>(v1, v2, false));
            }
        }

        return edges;
    }

    public List<T> getVertValues()
    {
        return getVertValues(verts.size());
    }

    public List<T> getVertValues(int n)
    {
        return verts.stream().parallel().limit(n).map(Vertex::getValue).collect(Collectors.toList());
    }

    public List<Vertex<T>> getVerts()
    {
        return verts;
    }
}
