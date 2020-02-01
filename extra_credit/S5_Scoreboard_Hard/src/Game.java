import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * A game consists a game name, exactly 2 teams and a score for each team. A game can change states such as current
 * period, score, playing/gameover. After a game ends a game can have a winner otherwise a winner is undecided. A
 * current period of -1 represents that the game has ended. A game is not started until the startGame() method is called
 *
 * @author Wil Simpson
 * @TODO redo javadocs for this class description
 */
public abstract class Game {
    /**
     * Index referred to for the home team
     */
    private static final int HOME_TEAM = 0;

    /**
     * Index referred to for the away team
     */
    private static final int AWAY_TEAM = 1;

    /**
     * The two teams playing the game. The HOME_TEAM and the AWAY_TEAM refers to which index is for each.
     */
    private Team[] teams = new Team[2];

    /**
     * The current score for the teams. The first index is the score for the home team and the second index is the score
     * for the second team
     */
    private int[] scores = {0, 0};

    /**
     * The period for the game
     */
    private Period period;

    /**
     * All valid scoring methods for the game. Order is kept consistent.
     */
    private List<ScoringMethod> scoringMethods = new LinkedList<>();


    /**
     * Creates a game given the parameters and sets the score for both teams to 0
     *
     * @param homeTeam  home team playing the game
     * @param awayTeam  away team playing the game
     * @param period    period for the game
     */
    public Game(Team homeTeam, Team awayTeam, Period period)
    {
        teams[HOME_TEAM] = homeTeam;
        teams[AWAY_TEAM] = awayTeam;
        this.period = period;
    }

    /**
     * Gets all available scoring methods for the game
     *
     * @return all available scoring methods for the game
     */
    public List<ScoringMethod> getScoringMethods()
    {
        return scoringMethods;
    }

    /**
     * Attempts to add the score for the given team. If the given team is not in the game nothing will happen and the
     * method will return false
     *
     * @param scoringMethod which scoring method was used to score points
     * @param team team to award the points to
     * @return whether points were successfully added to the given team
     */
    public boolean addScore(ScoringMethod scoringMethod, Team team)
    {
        if(!isTeamInGame(team)) return false;

        scores[getTeamIndex(team)] += scoringMethod.getScoringValue();
        return true;
    }

    /**
     * Gets the current winning team for the game. If there is a tie then null will be returned.
     *
     * @return current winning team or null if there is a tie
     */
    public Team getWinningTeam()
    {
        if(scores[HOME_TEAM] == scores[AWAY_TEAM]) return null;

        return scores[HOME_TEAM] > scores[AWAY_TEAM] ? teams[HOME_TEAM] : teams[AWAY_TEAM];
    }

    /**
     * Attempts to add the given scoring method to the array of scoring methods. Only is added if the given scoring method
     * is not already in the list
     *
     * @param method scoring method to add to array
     * @return whether the scoring method was successfully added
     */
    protected boolean addScoringMethod(ScoringMethod method)
    {
        return scoringMethods.add(method);
    }

    /**
     * Attempts to add all of the given scoring methods to the array of scoring methods. The methods are only added if
     * the current array does not contain that method.
     *
     * @param methods scoring methods to add
     * @return whether any methods were added to the array
     */
    protected boolean addScoringMethods(ScoringMethod...  methods)
    {
        return addScoringMethods(Arrays.asList(methods));
    }

    /**
     * Attempts to add all the given scoring methods to the array of scoring methods. Methods are only added if they are
     * not currently in the list.
     *
     * @param methods scoring methods to add to the array
     * @return whether any methods were added to the array
     */
    protected boolean addScoringMethods(List<ScoringMethod> methods)
    {
        return scoringMethods.addAll(methods);
    }

    /**
     * Gets the index of given team. Returns -1 if the team is not in the game
     *
     * @param team team to get index for
     * @return index of team in team array. -1 if the team is not in the array
     */
    protected int getTeamIndex(Team team)
    {
        for(int i=0; i<teams.length; i++)
        {
            if(teams[i] == team) return i;
        }

        return -1;
    }

    /**
     * Checks if the given team is in the current game
     *
     * @param team team to check
     * @return whether the team is in the game
     */
    public boolean isTeamInGame(Team team)
    {
        for(Team currentTeam : teams)
        {
            if(currentTeam == team)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether the game has started or not. A game has started after the current period exceeds 0
     *
     * @return whether the game has started
     */
    public boolean hasGameStarted()
    {
        return period.hasGameStarted();
    }

    /**
     * Starts the game by setting the current period to 1 if and only if the current period is 0 before being called.
     * If the current period is not 0 before calling this method nothing will happen and the method will return false.
     * If the current period is 0 before calling this method the current period will be set to 1 and the method will
     * return true.
     *
     * @return true if the current period is set to 1
     */
    public boolean startGame()
    {
        if(hasGameStarted() || getHomeTeam() == null || getAwayTeam() == null) return false;

        period.startGame();
        return true;
    }

    /**
     * Increments the current period to the next period if possible. If the game has not started or the game is over the
     * current period will not change.
     *
     * @return whether the current period has changed
     */
    public boolean endCurrentPeriod()
    {
        if(!hasGameStarted()) return false;
        if(isGameOver()) return false;

        period.nextPeriod();
        return true;
    }

    /**
     * Get the number of periods in the game
     *
     * @return number of periods in the game
     */
    public int getNumberOfPeriods()
    {
        return period.getNumberOfPeriods();
    }

    /**
     * Get the length of each period
     *
     * @return length of each period
     */
    public int getLengthOfPeriod()
    {
        return period.getLengthOfPeriod();
    }

    /**
     * Get the current period the game is in. The game is over when the current period is greater than the total number
     * of periods in the game.
     *
     * @return the current period the game is in
     */
    public int getCurrentPeriod()
    {
        return period.getCurrentPeriod();
    }

    /**
     * Checks whether the game is over and returns the result. The game is over when the current period greater than
     * the number of periods.
     *
     * @return true if the game has ended
     */
    public boolean isGameOver()
    {
        return period.isGameOver();
    }

    /**
     * Gets the two teams playing in the game. The first index is the home team and the second team is the away team.
     *
     * @return array of two teams playing in the game
     */
    public Team[] getTeams()
    {
        return teams;
    }

    /**
     * Get an array containing the current score of the game. The first index represents the score of the home team and
     * the second index refers to the score of the away team.
     *
     * @return scores for the two teams playing the game
     */
    public int[] getScores()
    {
        return scores;
    }

    /**
     * Replaces the two current teams with the given two teams. This keeps the current scores for the teams stays the
     * same
     *
     * @param homeTeam home team playing the game
     * @param awayTeam away team playing the game
     */
    public void setTeams(Team homeTeam, Team awayTeam)
    {
        teams[HOME_TEAM] = homeTeam;
        teams[AWAY_TEAM] = awayTeam;
    }

    /**
     * Sets the home team to the given team
     *
     * @param team new home team
     */
    public void setHomeTeam(Team team)
    {
        teams[HOME_TEAM] = team;
    }

    /**
     * Sets the away team to the given team
     *
     * @param team new away team
     */
    public void setAwayTeam(Team team)
    {
        teams[AWAY_TEAM] = team;
    }

    /**
     * Get the current home team playing the game
     *
     * @return current home team playing the game
     */
    public Team getHomeTeam()
    {
        return teams[HOME_TEAM];
    }

    /**
     * Get the current away team playing the game
     *
     * @return current away team playing the game
     */
    public Team getAwayTeam()
    {
        return teams[AWAY_TEAM];
    }


    /**
     * Get the current home team score
     *
     * @return current home team score
     */
    public int getScoreHome()
    {
        return scores[HOME_TEAM];
    }

    /**
     * Get the away home team score
     *
     * @return away home team score
     */
    public int getScoreAway()
    {
        return scores[AWAY_TEAM];
    }

    /**
     * The name of the game being played
     *
     * @return name of the game
     */
    public String getName()
    {
        return getClass().getName();
    }


    /**
     * Get a human readable string of the score
     *
     * @return human readable string of the score
     */
    public String scoreToString()
    {
        return "(HOME) "+getHomeTeam().getName()+": "+ getScoreHome()+" | "
                +"(AWAY) "+getAwayTeam().getName()+": "+ getScoreAway();
    }

    /**
     * Get a human readable string of the two teams playing the game without the score
     *
     * @return human readable string of the two teams playing the game
     */
    public String gameToString()
    {
        return "(HOME) "+getHomeTeam().getName()+" vs (AWAY) "+getAwayTeam().getName();
    }

    /**
     * Get the string representation of how we say the current period (ie- half, quarter, period, ect) of the game. If
     * the game is not started or the game is over then different messages will print.
     *
     * @return human readable expression of the current period
     */
    public String periodToString()
    {
        if(!hasGameStarted()) return "The game has not started yet!";
        if(isGameOver()) return "GAME OVER !!!";

        int periodNumber = getCurrentPeriod();

        return "Current "+ period.getName() +": "
                + (periodNumber != getNumberOfPeriods()+1 ? periodNumber : "Final");
    }

    public String getPeriodName()
    {
        return period.getName();
    }
}
