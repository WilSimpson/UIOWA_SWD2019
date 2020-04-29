import java.lang.reflect.Array;
import java.util.*;

public class GraphAlgos
{
    private UndirectedGraph<String> graph;

    public GraphAlgos(String inputFile)
    {
        graph = new UndirectedGraph(inputFile, (Comparator<String>) (s1, s2) -> {
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
        });
    }

    public UndirectedGraph getGraph()
    {
        return graph;
    }

    public List<String> findLargestConnectedSetVertsBFS()
    {
        LinkedList<String> largestConnectedSetVerts = new LinkedList<>();
        HashMap<String, Boolean> searched = new HashMap<>();
        LinkedList<String> queue = new LinkedList<>();

        for(String startingVert : graph.getVerts())
        {
            if(!searched.containsKey(startingVert))
            {
                searched.put(startingVert, true);
                queue.add(startingVert);
            }

            LinkedList<String> currentConnectVerts = new LinkedList<>();

            while(!queue.isEmpty())
            {

                String vert = queue.poll();
                currentConnectVerts.add(vert);
                for(String adjVert : graph.getAdjacencyList().get(vert))
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

    public List<String> findLargestConnectedSetVertsDFS()
    {
        LinkedList<String> largestConnectedSetVerts = new LinkedList<>();

        HashMap<String, Boolean> searched = new HashMap<>();

        for(String vert : graph.getVerts())
        {
            LinkedList<String> currentConnectedVerts = new LinkedList<>();
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

    private void findLargestConnectedSetVertsDFSRecursive(String vert, Map<String, Boolean> searched, List<String> currentConnectedVerts)
    {
        currentConnectedVerts.add(vert);
        searched.put(vert, true);
        for(String adjVert : graph.getAdjacencyList().get(vert))
        {
            if(!searched.containsKey(adjVert))
            {
                findLargestConnectedSetVertsDFSRecursive(adjVert, searched, currentConnectedVerts);
            }
        }
    }


    public void printLargestConnectedSetVerts(List<String> list)
    {
        for(String vert : list)
        {
            System.out.println(vert+":");
            printAdjacentVerts(vert);
            System.out.println("\n");
        }
    }

    public void printAdjacentVerts(String vert)
    {
        for(String adjVert : graph.getAdjacencyList().get(vert))
        {
            System.out.print(adjVert+", ");
        }
    }
}
