public class Basketball extends Game {
    /**
     * Creates a basketball game given two teams and sets the score for both teams to 0. Also adds all of the possible
     * scoring methods for the game.
     *
     * @param homeTeam        home team playing the game
     * @param awayTeam        away team playing the game
     */
    public Basketball(Team homeTeam, Team awayTeam) {
        super(homeTeam,
                awayTeam,
                2,
                20,
                "Half");

        addScoringMethods(ScoringMethod.TWO_POINT_BASKET,
                ScoringMethod.THREE_POINT_BASKET,
                ScoringMethod.FREE_THROW);
    }
}
