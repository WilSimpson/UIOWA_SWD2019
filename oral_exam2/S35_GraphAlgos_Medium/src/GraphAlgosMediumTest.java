public class GraphAlgosMediumTest
{
    public static void main(String[] args)
    {
        GraphAlgos algos = new GraphAlgos("words.dat");

        UndirectedGraph<String> graph = algos.getGraph();
        UndirectedGraph<String> largestGraphSubset = graph.findLargestSubsetBFS();

        System.out.println("BFS: "+largestGraphSubset.getVertValues(10));
        System.out.println("\nDFS: "+graph.findLargestSubsetDFS().getVertValues(10));
        System.out.println("\nSize of largest connected set: "+largestGraphSubset.getVerts().size());
        System.out.println("\nContents of largest set: "+largestGraphSubset.getVertValues());
    }
}
