import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GraphAlgos implements Comparator<String>
{
    private UndirectedGraph<String> graph;

    public GraphAlgos(String inputFile)
    {
        List<Vertex<String>> verts = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new java.io.FileReader(inputFile));
            String currentLine = "";
            while((currentLine = br.readLine()) != null)
            {
                verts.add(new Vertex<>(currentLine));
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        graph = new UndirectedGraph<String>(verts, this);
    }

    /**
    public List<Edge<String>> findMST()
    {
        UndirectedGraph<String> graph = this.graph.findLargestSubset();
        LinkedList<String> remainingVerts = new LinkedList<>(graph.getVerts());
        HashMap<String, List<String>> vertEdges = graph.getAdjacencyList();

        ArrayList<Edge<String>> mst = new ArrayList<>();
        mst.add(new Edge<>(remainingVerts.pop()));

        Edge<String> chosenEdge = null;
        String chosenVert = null;
        while(!remainingVerts.isEmpty())
        {

            //Found next closest vert
            remainingVerts.remove(chosenVert);
            mst.get(chosenVert).setEnd();
        }

        return null;
    }

     */

    public String findClosestVert(String baseVert, List<String> verts)
    {
        String currentClosest = null;

        for(String currentVert : verts)
        {
            currentClosest = getClosestEdge(baseVert, currentVert, currentClosest);
        }

        return currentClosest;
    }

    public UndirectedGraph<String> getGraph()
    {
        return graph;
    }

    @Override
    public int compare(String s1, String s2)
    {
        int numDifferences = 0;

        int s1Length = s1.length();
        int s2Length = s2.length();

        //Edit distance has to be more than one
        if(Math.abs(s1Length-s2Length) > 1)
            return -1;

        //At least one difference due to length
        if(Math.abs(s1Length-s2Length) == 1)
            numDifferences++;

        //We need to have separate indexes due to the lengths possibly being different by one
        int s1Index=0;
        int s2Index=0;
        while(s1Index<s1Length && s2Index<s2Length)
        {
            if(s1.charAt(s1Index) == s2.charAt(s2Index))
            {
                s1Index++;
                s2Index++;
            }
            else
            {
                numDifferences++;

                //We can leave early since we don't need to count
                if(numDifferences > 1) return -1;

                //Not being the same could be due to length issue.
                if(s1Length > s2Length)
                {
                    s1Index++;
                }
                else if(s2Index > s1Index)
                {
                    s2Index++;
                }
                //Not length issue, just different
                else
                {
                    s1Index++;
                    s2Index++;
                }
            }
        }

        return numDifferences == 1 ? 1 : -1;
    }

    /**
    public int getTotalWeight(List<Edge<String>> edges)
    {
        int total = 0;
        for(Edge edge : edges)
            total += getEdgeWeight(edge);

        return total;
    }
     */

    public String getClosestEdge(String baseVert, String vert1, String vert2)
    {
        if(baseVert == null || (vert1 == null  && vert2 == null)) return null;

        int weight1;
        int weight2;

        try
        {
            weight1 = getEdgeWeight(baseVert, vert2);
        }
        catch(NullPointerException ex)
        {
            return vert2;
        }

        try
        {
            weight2 = getEdgeWeight(baseVert, vert2);
        }
        catch(NullPointerException ex)
        {
            return vert1;
        }

        return weight1 < weight2 ? vert1 : vert2;
    }

    public int getEdgeWeight(String vert1, String vert2)
    {
        return Math.min(getVertMagnitude(vert1), getVertMagnitude(vert2));
    }

    /*
    public int getEdgeWeight(Edge<String> edge)
    {
        return getEdgeWeight(edge.getStart(), edge.getEnd());
    }

     */

    public int getVertMagnitude(String vert)
    {
        return vert.chars().sum();
    }
}
