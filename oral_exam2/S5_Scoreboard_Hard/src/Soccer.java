/**
 * A soccer game has two teams and has 4 periods, called halves and are 45 minutes each
 *
 * @author Wil Simpson
 */
public class Soccer extends Game
{
    /**
     * Creates a soccer game with the given teams and sets the score for both teams to 0. Also adds all of the possible
     * scoring methods for the game.
     *
     * @param homeTeam         home team playing the game
     * @param awayTeam         away team playing the game
     */
    public Soccer(Team homeTeam, Team awayTeam)
    {
        super(homeTeam, awayTeam, new Period("Half", 2, 45));

        addScoringMethods(new ScoringMethod("Goal", 1));
    }
}
