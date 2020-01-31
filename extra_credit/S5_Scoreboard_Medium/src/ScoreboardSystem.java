/**
 * The main driver for the program. This area is left open for use for developers to use the classes and functions as
 * needed. None of the contents of this main class is required to run the program. Contents here are soley for testing
 * purposes of the program.
 *
 * @author Wil Simpson
 */
public class ScoreboardSystem
{
    /**
     * DEBUGGING ONLY
     */
    private static Game[] games = new Game[4];

    /**
     * The starting point of the program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        Team hawks = new Team("Hawkeyes");
        Team cyclones = new Team("Cyclones");



        Game basketballGame = new Basketball(hawks, cyclones);
        games[0] = basketballGame;

        Game footballGame = new Football(hawks, cyclones);
        games[1] = footballGame;

        Game hockeyGame = new Hockey(hawks, cyclones);
        games[2] = hockeyGame;

        Game soccerGame = new Soccer(hawks, cyclones);
        games[3] = soccerGame;


        printGamesInfo();

        startGames();
        printGamesInfo();

        scoreGames();
        printGamesInfo();

        endPeriodGames();
        printGamesInfo();

        endPeriodGames();
        printGamesInfo();

        endPeriodGames();
        printGamesInfo();

        endPeriodGames();
        printGamesInfo();

        endPeriodGames();
        printGamesInfo();

    }

    /**
     * DEBUGGING ONLY
     */
    private static void endPeriodGames()
    {
        for(Game g : games)
        {
            g.endCurrentPeriod();
        }
    }

    /**
     * DEBUGGING ONLY
     */
    private static void template()
    {
        for(Game g : games)
        {

        }
    }

    /**
     * DEBUGGING ONLY
     */
    private static void scoreGames()
    {
        for(Game g : games)
        {
            g.addScore(g.getScoringMethods().get(0), g.getHomeTeam());
        }
    }

    /**
     * DEBUGGING ONLY
     */
    private static void startGames()
    {
        for(Game g : games)
        {
            g.startGame();
        }
    }

    /**
     * DEBUGGING ONLY
     */
    private static void printGamesInfo()
    {
        for(Game g : games)
        {
            p(g.getName()+"----|| "+g.gameToString()+" ||----");
            p(g.scoreToString());
            p(g.periodToString());
            if(g.isGameOver()) p("Winning team: "+g.getWinningTeam().getName());
            p("||--------------------------------------------------------------------||");
            p("");
        }

        p("#################################");
        p("#################################");
        p("#################################");
        p("#################################");
        p("");
    }

    /**
     * DEBUGGING ONLY
     */
    private static void p(Object o)
    {
        System.out.println(o);
    }
}
