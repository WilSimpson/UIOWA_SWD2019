import java.util.Comparator;

public class GraphAlgos
{
    private UndirectedGraph<String> graph;

    public GraphAlgos(String inputFile)
    {
        graph = new UndirectedGraph<>(inputFile, new Comparator<String>()
        {
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

                        if(s1Length > s2Length)
                        {
                            s1Index++;
                        }
                        else if(s2Index > s1Index)
                        {
                            s2Index++;
                        }
                        else
                        {
                            s1Index++;
                            s2Index++;
                        }
                    }
                }

                return numDifferences == 1 ? 1 : -1;
            }
        });
    }

    public UndirectedGraph getGraph()
    {
        return graph;
    }
}
