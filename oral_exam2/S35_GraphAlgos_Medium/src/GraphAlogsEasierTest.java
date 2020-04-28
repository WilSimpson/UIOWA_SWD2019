import java.util.HashMap;

public class GraphAlogsEasierTest
{
    public static void main(String[] args)
    {
        GraphAlgos algos = new GraphAlgos("words.dat");
        algos.createAdjacencyList();

        System.out.println("DFS Starting");
        algos.dfsFindLargestAdj();
        algos.printLargestAdjacencyList();
        System.out.println("DFS Runtime: "+algos.getAlgoRuntime()+" ms\n\n");

        System.out.println("BFS Starting");
        algos.bfsFindLargestAdj();
        algos.printLargestAdjacencyList();
        System.out.println("BFS Runtime: "+algos.getAlgoRuntime()+" ms");
    }
}
