public class Basketball extends Game {
    /**
     * Creates a basketball game given two teams and sets the score for both teams to 0. Also adds all of the possible
     * scoring methods for the game.
     *
     * @param homeTeam        home team playing the game
     * @param awayTeam        away team playing the game
     */
    public Basketball(Team homeTeam, Team awayTeam) {
        super(homeTeam, awayTeam, new Period("Half", 2, 20));

        addScoringMethods(new ScoringMethod("Two Point Basket", 2),
                new ScoringMethod("Three Point Basket", 3),
                new ScoringMethod("Free Throw", 1));
    }
}
