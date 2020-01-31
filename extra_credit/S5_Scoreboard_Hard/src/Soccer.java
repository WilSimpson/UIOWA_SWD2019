public class Soccer extends Game {
    /**
     * Creates a soccer game with the given teams and sets the score for both teams to 0. Also adds all of the possible
     * scoring methods for the game.
     *
     * @param homeTeam         home team playing the game
     * @param awayTeam         away team playing the game
     */
    public Soccer(Team homeTeam, Team awayTeam) {
        super(homeTeam, awayTeam, 2, 45, "Half");

        addScoringMethods(ScoringMethod.GOAL);
    }
}
