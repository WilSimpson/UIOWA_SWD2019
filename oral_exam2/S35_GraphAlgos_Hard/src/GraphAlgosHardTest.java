import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class GraphAlgosHardTest
{
    public static void main(String[] args)
    {
        //words.dat
        //words-short.dat
        //wordlist.dumb

        List<Vertex<String>> v = getVertsFromFile("S35_GraphALgos_Medium-Answers.dat");

        WeightedUndirectedGraph<String> graph =
                new WeightedUndirectedGraph<>(
                        getVertsFromFile("words.dat"),
                        getEdgeEvaluator(),
                        getEdgeWeightEvaluator()
                );

        graph.setVerts(graph.findLargestSubsetBFS());
        MST<String> mst = new MST<>(graph);

        System.out.println("Weight: "+mst.getWeight());

        Set<String> verts = new HashSet<>();
        for(Edge<String> e : mst.getEdges())
        {
            verts.add(e.getStart().getValue());
            verts.add(e.getEnd().getValue());
        }

        System.out.println("Off by: "+(mst.getWeight()-432100));

        System.out.println("#Verts: "+verts.size()+"/"+graph.getVerts().size());
        System.out.println("MySubset AnswerSubset Differences: "+isSimilar(v, graph.getVerts()));
    }

    public static List<String> isSimilar(List<Vertex<String>> l1, List<Vertex<String>> l2)
    {
        List<String> differences = new ArrayList<>();
        for(Vertex<String> v1 : l1)
        {
            boolean contained = false;
            for(Vertex<String> v2 : l2)
            {
                if(v1.getValue().equals(v2.getValue()))
                {
                    contained = true;
                    break;
                }
            }

            if(!contained) differences.add(v1.getValue());
        }

        return differences;
    }

    private static List<Vertex<String>> getVertsFromFile(String inputFile)
    {
        List<Vertex<String>> verts = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new java.io.FileReader(inputFile));
            String currentLine = "";
            while((currentLine = br.readLine()) != null)
            {
                verts.add(new Vertex<>(currentLine, false));
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return verts;
    }

    public static EdgeEvaluator<String> getEdgeEvaluator()
    {
        return new EdgeEvaluator<String>()
        {
            @Override
            public boolean validEdge(Edge<String> e1)
            {
                return validEdge(e1.getStart(), e1.getEnd());
            }

            @Override
            public boolean validEdge(SimpleVertex<String> v1, SimpleVertex<String> v2)
            {
                return validEdge(v1.getValue(), v2.getValue());
            }

            @Override
            public boolean validEdge(String s1, String s2)
            {
                int numDifferences = 0;

                int s1Length = s1.length();
                int s2Length = s2.length();

                //Edit distance has to be more than one
                if(Math.abs(s1Length-s2Length) > 1)
                    return false;

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
                        if(numDifferences > 1) return false;

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

                return numDifferences == 1;
            }
        };
    }

    public static int getVertMagnitude(String vert)
    {
        //return vert.chars().sum();

        char[] chars = vert.toCharArray();
        int val = 0;
        for(int i=0; i<chars.length; i++)
        {
            val += Character.getNumericValue(chars[i]);
        }

        return val;
    }

    public static EdgeWeightEvaluator<String> getEdgeWeightEvaluator()
    {
        return (v1, v2) -> Math.min(getVertMagnitude(v1.getValue()), getVertMagnitude(v2.getValue()));
    }
}
