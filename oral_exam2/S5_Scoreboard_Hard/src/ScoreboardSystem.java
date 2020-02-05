/**
 * The main driver for the program. This creates an array of all of the current game types and initializes them with no
 * teams playing. Then the simulator starts and asks for user input to start simulating games.
 *
 * @author Wil Simpson
 */
public class ScoreboardSystem
{
    /**
     * The starting point of the program. No command line arguments are needed or used.
     *
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        final Game[] allGames =
        {
                new Football(null, null),
                new Basketball(null, null),
                new Soccer(null, null),
                new Hockey(null, null)
        };

        GameSimulator simulator = new GameSimulator(allGames);
        simulator.startSimulation();
    }
}
