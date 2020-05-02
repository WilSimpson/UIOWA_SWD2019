import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * An example for how to create a minimum spanning tree from a fully connected undirected weighted graph.
 *
 * @author Wil Simpson
 */
public class GraphAlgosHardTest
{
    /**
     * Creates an undirected graph from an example file, finds the largest subset of vertices in the graph, finds
     * the minimum spanning tree and then prints the weight of the tree. No runtime arguments are used or checked for.
     *
     * @param args runtime arguments
     */
    public static void main(String[] args)
    {
        //words.dat
        //words-short.dat
        //wordlist.dumb

        WeightedUndirectedGraph<String> graph =
                new WeightedUndirectedGraph<>(
                        getVertsFromFile("words.dat"),
                        getEdgeEvaluator(),
                        getEdgeWeightEvaluator()
                );

        graph.setVerts(graph.findLargestSubsetBFS());
        MST<String> mst = new MST<>(graph);

        System.out.println("Weight: " + mst.getWeight());
    }

    /**
     * Creates a list of string vertices from an input file. A vertex is a single string line in the file.
     *
     * @param inputFile path to file
     * @return list of vertices created from the file
     */
    private static List<Vertex<String>> getVertsFromFile(String inputFile)
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

        return verts;
    }

    /**
     * Creates a string edge evaluator where a valid edge is present if two vertices edit distance is one. Strings do
     * not need to be the same length.
     *
     * @return new string edge evaluator
     */
    public static EdgeEvaluator<String> getEdgeEvaluator()
    {
        return new EdgeEvaluator<String>()
        {
            @Override
            public boolean validEdge(SimpleVertex<String> v1, SimpleVertex<String> v2)
            {
                return validEdge(v1.getValue(), v2.getValue());
            }

            @Override
            public boolean validEdge(String s1, String s2)
            {
                if(s1 == null || s2 == null) return false;

                boolean anyDiffs = false;

                int s1Length = s1.length();
                int s2Length = s2.length();

                //Edit distance has to be more than one
                if(Math.abs(s1Length - s2Length) > 1)
                    return false;

                //At least one difference due to length
                if(Math.abs(s1Length - s2Length) == 1)
                    anyDiffs = true;

                //We need to have separate indexes due to the lengths possibly being different by one
                int s1Index = 0;
                int s2Index = 0;
                while(s1Index < s1Length && s2Index < s2Length)
                {
                    if(s1.charAt(s1Index) == s2.charAt(s2Index))
                    {
                        s1Index++;
                        s2Index++;
                    }
                    else
                    {
                        //We can leave early since we don't need to count
                        if(anyDiffs) return false;

                        anyDiffs = true;

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

                return anyDiffs;
            }
        };
    }

    /**
     * Gets the weight of a string that represents a vertex
     *
     * @param vert string value of the vertex
     * @return weight of the vertex
     */
    public static int getVertMagnitude(String vert)
    {
        //return vert.chars().sum();

        char[] chars = vert.toCharArray();
        int val = 0;
        for(char c : chars)
        {
            val += Character.getNumericValue(c);
        }

        return val;
    }

    /**
     * Creates a new string edge evaluator that returns the minimum weight of either string in the edge
     *
     * @return new string edge evaluator
     */
    public static EdgeWeightEvaluator<String> getEdgeWeightEvaluator()
    {
        return (v1, v2) -> Math.min(getVertMagnitude(v1.getValue()), getVertMagnitude(v2.getValue()));
    }
}
