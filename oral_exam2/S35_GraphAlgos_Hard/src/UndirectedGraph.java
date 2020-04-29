import java.util.*;

public class UndirectedGraph<T> extends Graph<T>
{
    private ArrayList<Edge<T>> edges = new ArrayList<>();
    private HashMap<T, HashSet<T>> adjList;

    public UndirectedGraph(String inputFile, Comparator<T> comparator)
    {
        super(inputFile);

        adjList = new HashMap<>(getVerts().size());

        //Initialize adjList
        for(int i=0; i<getVerts().size(); i++)
        {
            adjList.put(getVerts().get(i), new HashSet<>());
        }

        //Create edges
        for(int i=0; i<getVerts().size(); i++)
        {
            for(int j=i+1; j<getVerts().size(); j++)
            {
                if(comparator.compare(getVerts().get(i), getVerts().get(j)) > 0)
                {
                    edges.add(new Edge<>(getVerts().get(i), getVerts().get(j)));
                    edges.add(new Edge<>(getVerts().get(j), getVerts().get(i)));
                }
            }
        }

        for(int i=0; i<edges.size(); i++)
        {
            T start = edges.get(i).getStart();
            T end = edges.get(i).getEnd();

            adjList.get(start).add(end);
            adjList.get(end).add(start);
        }
    }

    public int getNumberEdges(T vert)
    {
        return adjList.get(vert).size();
    }

    public double getAverageNumEdges()
    {
        double total = 0;
        for(T vert : adjList.keySet())
        {
            total += adjList.get(vert).size();
        }

        return getVerts().size()/total;
    }

    public HashMap<T, HashSet<T>> getAdjacencyList()
    {
        return adjList;
    }

    public ArrayList<Edge<T>> getEdges()
    {
        return edges;
    }

    public List<T> findLargestConnectedSetVertsBFS()
    {
        LinkedList<T> largestConnectedSetVerts = new LinkedList<>();
        HashMap<T, Boolean> searched = new HashMap<>();
        LinkedList<T> queue = new LinkedList<>();

        for(T startingVert : getVerts())
        {
            if(!searched.containsKey(startingVert))
            {
                searched.put(startingVert, true);
                queue.add(startingVert);
            }

            LinkedList<T> currentConnectVerts = new LinkedList<>();

            while(!queue.isEmpty())
            {

                T vert = queue.poll();
                currentConnectVerts.add(vert);
                for(T adjVert : getAdjacencyList().get(vert))
                {
                    if(!searched.containsKey(adjVert))
                    {
                        searched.put(adjVert, true);
                        queue.add(adjVert);
                    }
                }

                if(currentConnectVerts.size() > largestConnectedSetVerts.size())
                    largestConnectedSetVerts = currentConnectVerts;
            }
        }

        return largestConnectedSetVerts;
    }

    public List<T> findLargestConnectedSetVertsDFS()
    {
        LinkedList<T> largestConnectedSetVerts = new LinkedList<>();

        HashMap<T, Boolean> searched = new HashMap<>();

        for(T vert : getVerts())
        {
            LinkedList<T> currentConnectedVerts = new LinkedList<>();
            if(!searched.containsKey(vert))
            {
                searched.put(vert, true);
                findLargestConnectedSetVertsDFSRecursive(vert, searched, currentConnectedVerts);

                if(currentConnectedVerts.size() > largestConnectedSetVerts.size())
                    largestConnectedSetVerts = currentConnectedVerts;
            }
        }

        return largestConnectedSetVerts;
    }

    private void findLargestConnectedSetVertsDFSRecursive(T vert, Map<T, Boolean> searched, List<T> currentConnectedVerts)
    {
        currentConnectedVerts.add(vert);
        searched.put(vert, true);
        for(T adjVert : getAdjacencyList().get(vert))
        {
            if(!searched.containsKey(adjVert))
            {
                findLargestConnectedSetVertsDFSRecursive(adjVert, searched, currentConnectedVerts);
            }
        }
    }
}
