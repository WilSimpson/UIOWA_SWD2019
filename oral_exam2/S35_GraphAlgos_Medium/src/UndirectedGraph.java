import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class UndirectedGraph<T>
{
    private ArrayList<T> verts = new ArrayList<>();
    private ArrayList<Edge<T>> edges = new ArrayList<>();
    private HashMap<T, HashSet<T>> adjList;

    public UndirectedGraph(String inputFile, Comparator<T> comparator)
    {
        try
        {
            BufferedReader br = new BufferedReader(new java.io.FileReader(inputFile));
            String currentLine = "";
            while((currentLine = br.readLine()) != null)
            {
                verts.add((T) currentLine);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        adjList = new HashMap<>(verts.size());

        //Initialize adjList
        for(int i=0; i<verts.size(); i++)
        {
            adjList.put(verts.get(i), new HashSet<>());
        }

        //Create edges
        for(int i=0; i<verts.size(); i++)
        {
            for(int j=i+1; j<verts.size(); j++)
            {
                if(comparator.compare(verts.get(i), verts.get(j)) > 0)
                {
                    edges.add(new Edge<>(verts.get(i), verts.get(j)));
                    edges.add(new Edge<>(verts.get(j), verts.get(i)));
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

        return verts.size()/total;
    }

    public HashMap<T, HashSet<T>> getAdjacencyList()
    {
        return adjList;
    }

    public ArrayList<Edge<T>> getEdges()
    {
        return edges;
    }

    public ArrayList<T> getVerts()
    {
        return verts;
    }
}
