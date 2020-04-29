import java.util.List;

public class GraphAlgosHardTest
{
    public static void main(String[] args)
    {
        GraphAlgos algos = new GraphAlgos("words.dat");
        UndirectedGraph<String> graph = algos.getGraph();
        //GraphAlgos algos = new GraphAlgos("words-short.dat");
        //GraphAlgos algos = new GraphAlgos("wordlist.dumb");
        //System.out.println(algos.getGraph().getAverageNumEdges());

        List<String> list;

        System.out.print("BFS: ");
        list = graph.findLargestConnectedSetVertsBFS();
        System.out.println(list);

        System.out.print("DFS: ");
        list = graph.findLargestConnectedSetVertsDFS();
        System.out.println(list);

        //list = algos.getAllConnectedVerts(list);

        System.out.println("Size of largest connected set: "+list.size());


    }
}
