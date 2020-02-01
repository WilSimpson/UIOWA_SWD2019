import java.util.List;
import java.util.Scanner;

public class GameSimulator
{
    private Scanner scanner = new Scanner(System.in);
    private Game[] games;
    private Game selectedGame;

    public GameSimulator(Game[] games)
    {
        this.games = games;
    }

    public void startSimulation()
    {
        Menu currentMenu = new Menu("Select the type of game");
        for(int i=0; i<games.length; i++)
        {
            currentMenu.addMenuOption(games[i].getName(), games);
        }

        //Start menu for selecting the current game
        selectedGame = games[currentMenu.getChoice()];




        //Get the home team
        System.out.print("Enter home team name: ");
        selectedGame.setHomeTeam(new Team(scanner.nextLine()));

        //Get the away team
        System.out.print("Enter away team name: ");
        selectedGame.setAwayTeam(new Team(scanner.nextLine()));

        selectedGame.startGame();

        System.out.println("\n");
        printCurrentData();

        //Create final simulation menu
        currentMenu = new Menu("Menu");

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


        while(!selectedGame.isGameOver())
        {
            int optionChoice = currentMenu.getChoice();

            if(optionChoice < scoringMethods.size())
            {
                selectedGame.addScore(scoringMethods.get(optionChoice), selectedGame.getHomeTeam());
            }
            else if(optionChoice < scoringMethods.size()*2)
            {
                selectedGame.addScore(scoringMethods.get(optionChoice-scoringMethods.size()), selectedGame.getAwayTeam());
            }
            else
            {
                selectedGame.endCurrentPeriod();
            }

            printCurrentData();
        }

        System.out.println("Winner: "+
                ((selectedGame.getWinningTeam() != null) ? selectedGame.getWinningTeam().getName() : "Tie")
            );

        /**

        //Keep asking the user for input until the game has ended!
        while(!selectedGame.isGameOver())
        {
            printCurrentData();
            int choiceIndex = currentMenu.getChoice();

            //If it's the first set of scoring
        }


        */

    }

    private void printCurrentData()
    {
        System.out.println("\n"+selectedGame.scoreToString()
                          +"\n"+selectedGame.periodToString()+"\n");
    }
}
