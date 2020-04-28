import java.util.ArrayList;
import java.util.HashMap;

public class GraphAlogsEasierTest
{
    public static void main(String[] args)
    {
        GraphAlgos algos = new GraphAlgos("words-short.dat");
        //System.out.println(algos.getGraph().getAverageNumEdges());

        ArrayList<String> list;

        System.out.println("Running BFS:");
        list = algos.findLargestConnectedSetVertsBFS();
        System.out.println("\n\nLargest set of verts found:");
        algos.printLargestConnectedSetVerts(list);

        System.out.println("\n\nRunning DFS:");
        list = algos.findLargestConnectedSetVertsDFS();
        System.out.println("\n\nLargest set of verts found:");
        algos.printLargestConnectedSetVerts(list);
    }
}
