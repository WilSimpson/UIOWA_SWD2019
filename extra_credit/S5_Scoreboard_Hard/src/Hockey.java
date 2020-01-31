public class Hockey extends Game
{

    /**
     * Creates a hockey game with the given teams and sets the score for both teams to 0. Also adds all of the possible
     * scoring methods for the game.
     *
     * @param homeTeam         home team playing the game
     * @param awayTeam         away team playing the game
     */
    public Hockey(Team homeTeam, Team awayTeam) {
        super(homeTeam, awayTeam, 3, 20, "Period");

        addScoringMethods(ScoringMethod.GOAL);
    }
}
