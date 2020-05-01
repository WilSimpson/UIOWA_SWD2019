import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GraphAlgosMediumTest
{
    /**
     * Demonstrates how graphs can be created with a given list of vertices.
     *
     * @param args runtime arguments
     */
    public static void main(String[] args)
    {
        //words.dat
        //words-short.dat
        //wordlist.dumb

        UndirectedGraph<String> graph =
                new UndirectedGraph<>(
                        getVertsFromFile("words.dat"),
                        getEdgeEvaluator()
                );

        List<Vertex<String>> adjList = graph.findLargestSubsetBFS();
        System.out.println("BFS: "+vertsToStrings(adjList, 10));
        System.out.println("DFS: "+vertsToStrings(graph.findLargestSubsetDFS(), 10));
        System.out.println("NUM VERTS: "+adjList.size());
        System.out.println("ALL VERTS: "+vertsToStrings(adjList));
    }

    /**
     * Changes a list of vertices to a list of string
     *
     * @param verts vertices to change
     * @return list of string values of vertices
     */
    private static List<String> vertsToStrings(List<Vertex<String>> verts)
    {
        return verts.stream().map(Vertex::getValue).collect(Collectors.toList());
    }

    /**
     * Changes a list of vertices to a list of string up to a maximum index
     *
     * @param verts vertices to change
     * @param i maximum index
     * @return list of string values of vertices
     */
    private static List<String> vertsToStrings(List<Vertex<String>> verts, int i)
    {
        return verts.stream().limit(i).map(Vertex::getValue).collect(Collectors.toList());
    }

    /**
     * Creates a list of vertices given an file. A String Vertex will be created every new line.
     *
     * @param inputFile file to use as input
     * @return list of vertices create from the file
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
     * Creates an example edge evaluator. This edge evaluator checks if the edit distance between two vertices is
     * exactly one.
     *
     * @return new EdgeEvaluator
     */
    public static EdgeEvaluator<String> getEdgeEvaluator()
    {
        return (s1, s2) -> {

            if(s1 == null || s2 == null) return false;

            boolean anyDiffs = false;

            int s1Length = s1.length();
            int s2Length = s2.length();

            //Edit distance has to be more than one
            if(Math.abs(s1Length-s2Length) > 1)
                return false;

            //At least one difference due to length
            if(Math.abs(s1Length-s2Length) == 1)
                anyDiffs = true;

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
        };
    }
}
