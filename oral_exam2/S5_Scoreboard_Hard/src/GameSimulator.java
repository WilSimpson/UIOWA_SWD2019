import java.util.List;
import java.util.Scanner;

/**
 * A game simulator handles all parts of the game simulation including handling input and output.
 *
 * @author Wil Simpson
 */
public class GameSimulator
{
    /**
     * Scanner that deals with input and output
     */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * All possible games that can be simulated
     */
    private Game[] games;

    /**
     * Game selected to be simulated
     */
    private Game selectedGame;

    /**
     * Creates a new game simulator given all games you'd possibly like to simulate
     *
     * @param games all games you may like to possibly simulate
     */
    public GameSimulator(Game[] games)
    {
        this.games = games;
    }

    /**
     * Runs a simluation for a game. Handles user input and output. Asks to select the type of game and the two teams.
     * Then it asks to choose options from a list that effect the game. The simulation runs until the end of the game
     * is reached. Once the game ends the final score as well as the winning team is shown. If the game ends in a tie
     * then that is shown instead.
     */
    public void startSimulation()
    {
        //Creates the game selecting menu
        Menu currentMenu = new Menu("Select the type of game");
        for(Game game : games)
        {
            currentMenu.addMenuOption(game.getName(), games);
        }

        //Start menu for selecting the current game
        selectedGame = games[currentMenu.getChoice()];


        //Get the home team
        System.out.print("Enter home team name: ");
        selectedGame.setHomeTeam(new Team(SCANNER.nextLine()));

        //Get the away team
        System.out.print("Enter away team name: ");
        selectedGame.setAwayTeam(new Team(SCANNER.nextLine()));

        //Immediately start the game once all required information is entered
        selectedGame.startGame();

        System.out.println("\n");
        printCurrentData();

        //The old menu is no longer needed so we overwrite it with the final simulation menu
        currentMenu = new Menu("Menu");

        //All posible scoring methods
        List<ScoringMethod> scoringMethods = selectedGame.getScoringMethods();

        //Populate the menu
        for(int i=0; i<scoringMethods.size(); i++)
        {
            ScoringMethod scoringMethod = scoringMethods.get(i);
            currentMenu.addMenuOption(selectedGame.getHomeTeam().getName()+" "+scoringMethod.getName(), i);
        }
        for(int i=0; i<scoringMethods.size(); i++)
        {
            ScoringMethod scoringMethod = scoringMethods.get(i);
            currentMenu.addMenuOption(selectedGame.getAwayTeam().getName()+" "+scoringMethod.getName(), i);
        }
        currentMenu.addMenuOption("End "+selectedGame.getPeriodName(), currentMenu.getNumberOptions());

        //Start looping and asking for user input to simulate a game
        //The loop will continue until the game has ended
        while(!selectedGame.isGameOver())
        {
            //Only a valid choice choice can be given
            int optionChoice = currentMenu.getChoice();

            //The first set of scoring methods is for the home team
            if(optionChoice < scoringMethods.size())
            {
                selectedGame.addScore(scoringMethods.get(optionChoice), selectedGame.getHomeTeam());
            }
            //The second set of scoring methods is for the away team
            else if(optionChoice < scoringMethods.size()*2)
            {
                selectedGame.addScore(scoringMethods.get(optionChoice-scoringMethods.size()), selectedGame.getAwayTeam());
            }
            //The final selection option is to end the current period
            else
            {
                selectedGame.endCurrentPeriod();
            }

            //Show the current status of the simulation
            printCurrentData();
        }

        //Show the winner and end the simulation
        System.out.println("Winner: "+
                ((selectedGame.getWinningTeam() != null) ? selectedGame.getWinningTeam().getName() : "Tie")
            );
    }

    /**
     * Prints the current state of the game such as the home team, their score, the away team, their score and the
     * current period of the game.
     */
    private void printCurrentData()
    {
        System.out.println("\n"+selectedGame.scoreToString()
                          +"\n"+selectedGame.periodToString()+"\n");
    }
}
