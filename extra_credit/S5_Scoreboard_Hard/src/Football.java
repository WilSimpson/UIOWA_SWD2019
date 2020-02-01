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
        super(homeTeam, awayTeam, new Period("Quarter", 4, 15));

        addScoringMethods(new ScoringMethod("Touchdown", 6),
                new ScoringMethod("Extra Point", 1),
                new ScoringMethod("Field Goal", 3),
                new ScoringMethod("Safety", 2),
                new ScoringMethod("Two Point Conversion", 2));
    }

}
