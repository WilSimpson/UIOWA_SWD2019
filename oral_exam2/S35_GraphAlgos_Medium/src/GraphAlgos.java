import java.lang.reflect.Array;
import java.util.*;

public class GraphAlgos
{
    private int currentVertsPerLine = 0;
    private int maxVertsPerLine = 10;

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

    public ArrayList<String> findLargestConnectedSetVertsBFS()
    {
        ArrayList<String> largestConnectedSetVerts = new ArrayList<>();
        HashMap<String, Boolean> searched = new HashMap<>();
        LinkedList<String> queue = new LinkedList<>();

        for(String vert : graph.getVerts())
        {
            if(!searched.containsKey(vert))
            {
                queue.add(vert);
                searched.put(vert, true);
                findLargestConnectedSetVertsBFSRecursive(queue, searched, largestConnectedSetVerts);
            }
        }

        currentVertsPerLine = 0;
        return largestConnectedSetVerts;
    }

    private void findLargestConnectedSetVertsBFSRecursive(LinkedList<String> queue, HashMap<String, Boolean> searched, ArrayList<String> largestConnectedSetVerts)
    {
        //We're done processing the queue
        if(queue.isEmpty()) return;

        String vert = queue.poll();
        checkForLargestVert(vert, searched, largestConnectedSetVerts);

        for(String adjVert : graph.getAdjacencyList().get(vert))
        {
            if(!searched.containsKey(adjVert))
            {
                searched.put(adjVert, true);
                queue.add(adjVert);
            }
        }

        findLargestConnectedSetVertsBFSRecursive(queue, searched, largestConnectedSetVerts);
    }

    public ArrayList<String> findLargestConnectedSetVertsDFS()
    {
        ArrayList<String> largestConnectedSetVerts = new ArrayList<>();
        HashMap<String, Boolean> searched = new HashMap<>();

        for(String vert : graph.getVerts())
        {
            if(!searched.containsKey(vert))
                findLargestConnectedSetVertsDFSRecursive(vert, searched, largestConnectedSetVerts);
        }

        currentVertsPerLine = 0;
        return largestConnectedSetVerts;
    }

    private void findLargestConnectedSetVertsDFSRecursive(String vert, HashMap<String, Boolean> searched, ArrayList<String> largestConnectedSetVerts)
    {
        checkForLargestVert(vert, searched, largestConnectedSetVerts);

        for(String adjVert : graph.getAdjacencyList().get(vert))
        {
            if(!searched.containsKey(adjVert))
            {
                findLargestConnectedSetVertsDFSRecursive(adjVert, searched, largestConnectedSetVerts);
            }
        }
    }

    public void checkForLargestVert(String vert, HashMap<String, Boolean> searched, ArrayList<String> largestConnectedSetVerts)
    {
        System.out.print(vert+getSpacerType());

        //Mark the vert as searched
        searched.put(vert, true);

        //Check current and current largest set of verts
        int currentLargestNumVertEdges = 0;
        if(largestConnectedSetVerts.size() > 0)
            currentLargestNumVertEdges = graph.getAdjacencyList().get(largestConnectedSetVerts.get(0)).size();

        int currentNumVertEdges = graph.getAdjacencyList().get(vert).size();

        //If larger clear and add current
        if(currentNumVertEdges > currentLargestNumVertEdges)
        {
            largestConnectedSetVerts.clear();
            largestConnectedSetVerts.add(vert);
        }
        //They're the same, add it to the list
        else if(currentNumVertEdges == currentLargestNumVertEdges)
        {
            largestConnectedSetVerts.add(vert);
        }
    }


    public void printLargestConnectedSetVerts(ArrayList<String> list)
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
            System.out.print(adjVert+getSpacerType());
        }

        currentVertsPerLine = 0;
    }

    private String getSpacerType()
    {
        String spacer;
        if(currentVertsPerLine < maxVertsPerLine)
        {
            currentVertsPerLine++;
            spacer = ", ";
        }
        else
        {
            currentVertsPerLine = 0;
            spacer = "\n";
        }

        return spacer;
    }
}
