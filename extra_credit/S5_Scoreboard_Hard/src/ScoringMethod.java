public class ScoringMethod
{
    /**
     * Amount of points the scoring method is worth
     */
    private final int value;

    /**
     * Name of the scoring method
     */
    private final String name;

    public ScoringMethod(String name, int value)
    {
        this.name = name;
        this.value = value;
    }



    /**
     * Gets the score value of the scoring method
     *
     * @return score value of scoring method
     */
    public int getScoringValue()
    {
        return value;
    }

    public String getName()
    {
        return name;
    }
}
