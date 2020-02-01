/**
 * The main driver for the program. This area is left open for use for developers to use the classes and functions as
 * needed. None of the contents of this main class is required to run the program. Contents here are soley for testing
 * purposes of the program.
 *
 * @author Wil Simpson
 * @todo Update javadocs to reflect change from medium -> hard
 */
public class ScoreboardSystem
{
    /**
     * The starting point of the program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        final Game[] allGames = {
                new Football(null, null),
                new Basketball(null, null),
                new Soccer(null, null),
                new Hockey(null, null)
        };

        GameSimulator simulator = new GameSimulator(allGames);

        simulator.startSimulation();
    }
}
