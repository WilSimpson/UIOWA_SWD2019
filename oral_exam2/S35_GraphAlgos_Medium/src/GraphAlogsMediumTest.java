import java.util.Arrays;
import java.util.List;

public class GraphAlogsMediumTest
{
    public static void main(String[] args)
    {
        GraphAlgos algos = new GraphAlgos("words.dat");
        //GraphAlgos algos = new GraphAlgos("words-short.dat");
        //GraphAlgos algos = new GraphAlgos("wordlist.dumb");
        //System.out.println(algos.getGraph().getAverageNumEdges());

        List<String> list;

        System.out.print("BFS: ");
        list = algos.getGraph().findLargestConnectedSetVertsBFS();
        System.out.println(list);

        System.out.print("DFS: ");
        list = algos.getGraph().findLargestConnectedSetVertsDFS();
        System.out.println(list);

        //list = algos.getAllConnectedVerts(list);

        System.out.println("Size of largest connected set: "+list.size());


    }
}
