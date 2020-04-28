import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class GraphAlgos
{
    private ArrayList<String> words;

    private HashMap<String, ArrayList<String>> adjacencyList = new HashMap<>();
    private HashMap<String, Boolean> searched = new HashMap<>();
    private ArrayList<String> largestAdjacenyList = new ArrayList<>();

    private long startTime;
    private long endTime;

    public GraphAlgos(String filePath)
    {
        words = FileReader.fileToList(filePath);
    }

    public void createAdjacencyList()
    {
        for(String keyWord : words)
        {
            for(String comparedWord : words)
            {
                if(!keyWord.equals(comparedWord) && comparedWord != null)
                {
                    if(editDistanceIsOne(keyWord, comparedWord))
                    {
                        ArrayList<String> adjacentVerts = adjacencyList.get(keyWord);
                        if(adjacentVerts == null)
                        {
                            adjacentVerts = new ArrayList<>();
                        }

                        adjacentVerts.add(comparedWord);
                        adjacencyList.put(keyWord, adjacentVerts);
                    }
                }
            }
        }
    }

    public long getAlgoRuntime()
    {
        return endTime - startTime;
    }

    public void clearLargestAdjacencyList()
    {
        largestAdjacenyList.clear();
    }

    public ArrayList<String> getLargestAdjacenyList()
    {
        return largestAdjacenyList;
    }

    public void bfsFindLargestAdj()
    {
        startTime = System.currentTimeMillis();

        searched.clear();
        largestAdjacenyList.clear();

        for(String vert : adjacencyList.keySet())
        {
            if(searched.get(vert) == null || !searched.get(vert))
            {
                bfsFindLargestAdjHelper(vert);
            }
        }

        endTime = System.currentTimeMillis();
    }

    private void bfsFindLargestAdjHelper(String vert)
    {
        searched.put(vert, true);
        LinkedList<String> vertBuffer = new LinkedList<>();
        vertBuffer.add(vert);

        while(vertBuffer.size() != 0)
        {
            vert = vertBuffer.poll();
            int currentLargestVert = currentLargestAdjVerts();
            int currentAdjVerts = getNumAdj(vert);

            if(currentAdjVerts > currentLargestVert)
            {
                largestAdjacenyList.clear();
                largestAdjacenyList.add(vert);
            }
            //Same as largest
            else if(currentAdjVerts == currentLargestVert)
            {
                largestAdjacenyList.add(vert);
            }

            for(String adjVert : adjacencyList.get(vert))
            {
                if(searched.get(adjVert) == null || !searched.get(adjVert))
                {
                    searched.put(adjVert, true);
                    vertBuffer.add(adjVert);
                }
            }
        }

        vertBuffer.add(vert);
    }

    public void dfsFindLargestAdj()
    {
        startTime = System.currentTimeMillis();

        searched.clear();
        largestAdjacenyList.clear();

        for(String vert : adjacencyList.keySet())
        {
            if(searched.get(vert) == null || !searched.get(vert))
            {
                dfsFindLargestAdjRecursiveHelper(vert);
            }
        }

        endTime = System.currentTimeMillis();
    }

    private void dfsFindLargestAdjRecursiveHelper(String vert)
    {
        searched.put(vert, true);
        int currentLargestVert = currentLargestAdjVerts();
        int currentAdjVerts = getNumAdj(vert);

        //Current vert is the largest
        if(currentAdjVerts > currentLargestVert)
        {
            largestAdjacenyList.clear();
            largestAdjacenyList.add(vert);
        }
        //Same as largest
        else if(currentAdjVerts == currentLargestVert)
        {
            largestAdjacenyList.add(vert);
        }

        for(String adj : adjacencyList.get(vert))
        {
            if(searched.get(adj) == null || !searched.get(adj))
            {
                dfsFindLargestAdjRecursiveHelper(adj);
            }
        }
    }

    public int getNumAdj(String vert)
    {
        return adjacencyList.get(vert).size();
    }

    private int currentLargestAdjVerts()
    {
        for(String vert : largestAdjacenyList)
        {
            return adjacencyList.get(vert).size();
        }

        return 0;
    }

    public HashMap<String, ArrayList<String>> getAdjacencyList()
    {
        return adjacencyList;
    }

    public boolean editDistanceIsOne(String s1, String s2)
    {
        int numDifferences = 0;
        for(int i=0; i<s1.length(); i++)
        {
            if(s1.charAt(i) == s2.charAt(i)) numDifferences++;
        }

        return numDifferences == 1;
    }

    public void printLargestAdjacencyList()
    {
        for(String currentVert : largestAdjacenyList)
        {
            System.out.println(currentVert);
            for(String adjVert : adjacencyList.get(currentVert))
            {
                System.out.print(adjVert+", ");
            }
            System.out.println("");
        }
    }
}
