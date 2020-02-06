/**
 * A scoring method consists of a name and a value. The same scoring method can be used for multiple games and the
 * the values are intended to stay the same without changing. An enum was not used since some games use the same name for
 * scoring methods but have different scoring values. Also scoring methods can easily be implemented to have more
 * features
 */
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

    /**
     * Creates a new scoring method given a name and value
     *
     * @param name name for the method
     * @param value point value for the score
     */
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

    /**
     * Gets the name of the scoring method
     *
     * @return name of the scoring method
     */
    public String getName()
    {
        return name;
    }
}
