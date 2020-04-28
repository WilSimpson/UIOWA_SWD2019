public class GraphAlogsEasierTest
{
    public static void main(String[] args)
    {
        GraphAlgos algos = new GraphAlgos("words-short.dat");
        System.out.println(algos.getGraph().getAverageNumEdges());
    }
}
