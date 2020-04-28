import java.util.HashMap;

public class GraphAlogsEasierTest
{
    public static void main(String[] args)
    {
        GraphAlgos algos = new GraphAlgos("words-short.dat");
        //System.out.println(algos.getGraph().getAverageNumEdges());

        System.out.println("Running BFS:");
        algos.findLargestConnectedSetVertsBFS();
        System.out.println("\n\nLargest set of verts found:");
        algos.printLargestConnectedSetVerts();

        System.out.println("\n\nRunning DFS:");
        algos.findLargestConnectedSetVertsDFS();
        System.out.println("\n\nLargest set of verts found:");
        algos.printLargestConnectedSetVerts();
    }
}
