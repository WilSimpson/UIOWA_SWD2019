import java.util.*;
import java.util.stream.Collectors;

public class UndirectedGraph<T>
{
    private List<Vertex<T>> verts;

    public UndirectedGraph(List<Vertex<T>> verts)
    {
        this.verts = verts;
    }

    public UndirectedGraph(List<Vertex<T>> verts, Comparator<T> comparator)
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
                        comparator.compare(vert1.getValue(), vert2.getValue()) > 0)
                {
                    vert1.addEdge(vert2);
                    vert2.addEdge(vert1);
                }
            }
        }
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

    public UndirectedGraph<T> findLargestSubsetBFS()
    {
        LinkedList<Vertex<T>> largestConnectedSetVerts = new LinkedList<>();
        HashMap<T, Boolean> searched = new HashMap<>();
        LinkedList<Vertex<T>> queue = new LinkedList<>();

        for(Vertex<T> startingVert : getVerts())
        {
            if(!searched.containsKey(startingVert.getValue()))
            {
                searched.put(startingVert.getValue(), true);
                queue.add(startingVert);
            }

            LinkedList<Vertex<T>> currentConnectedVerts = new LinkedList<>();

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

        return new UndirectedGraph<>(largestConnectedSetVerts);
    }

    public UndirectedGraph<T> findLargestSubsetDFS()
    {
        LinkedList<Vertex<T>> largestConnectedSetVerts = new LinkedList<>();
        HashMap<T, Boolean> searched = new HashMap<>();

        for(Vertex<T> vert : getVerts())
        {
            LinkedList<Vertex<T>> currentConnectedVerts = new LinkedList<>();
            if(!searched.containsKey(vert.getValue()))
            {
                searched.put(vert.getValue(), true);
                findLargestSubsetDFSRecursive(vert, searched, currentConnectedVerts);

                if(currentConnectedVerts.size() > largestConnectedSetVerts.size())
                    largestConnectedSetVerts = currentConnectedVerts;
            }
        }

        return new UndirectedGraph<>(largestConnectedSetVerts);
    }

    private void findLargestSubsetDFSRecursive(Vertex<T> vert, Map<T, Boolean> searched, LinkedList<Vertex<T>> currentConnectedVerts)
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
