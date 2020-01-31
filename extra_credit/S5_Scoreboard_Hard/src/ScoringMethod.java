public enum ScoringMethod
{
    //Football scoring methods
    TOUCHDOWN(7),
    FIELD_GOAL(3),
    EXTRA_POINT(1),
    TWO_POINT_CONVERSION(2),
    SAFETY(2),

    //Basketball scoring methods
    TWO_POINT_BASKET(2),
    THREE_POINT_BASKET(3),
    FREE_THROW(1),

    //Soccer and Hockey scoring methods
    GOAL(1);

    /**
     * Amount of points the scoring method is worth
     */
    private int scoringValue;

    /**
     * Creates a new scoring method with the given scoring value
     * @param scoringValue
     */
    private ScoringMethod(int scoringValue)
    {
        this.scoringValue = scoringValue;
    }

    /**
     * Gets the score value of the scoring method
     *
     * @return score value of scoring method
     */
    public int getScoringValue()
    {
        return scoringValue;
    }
}
