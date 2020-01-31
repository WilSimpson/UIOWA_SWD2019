/**
 * @author Wil Simpson
 */
public class Football extends Game
{
    /**
     * Creates a football game given the parameters and sets the score for both teams to 0. Also adds all of the
     * possible scoring methods for the game.
     *
     * @param homeTeam home team playing the game
     * @param awayTeam away team playing the game
     */
    public Football(Team homeTeam, Team awayTeam) {
        super(homeTeam,
                awayTeam,
                4,
                15,
                "Quarter");

        addScoringMethods(ScoringMethod.TOUCHDOWN,
                ScoringMethod.EXTRA_POINT,
                ScoringMethod.FIELD_GOAL,
                ScoringMethod.SAFETY,
                ScoringMethod.TWO_POINT_CONVERSION);
    }

}
