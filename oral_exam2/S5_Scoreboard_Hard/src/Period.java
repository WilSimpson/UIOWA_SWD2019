public class Period {
    /**
     * The name for each period
     */
    private final String name;

    /**
     * The total number of periods
     */
    private final int numberOfPeriods;

    /**
     * The time length in minutes for each period
     */
    private final int lengthOfPeriod;

    /**
     * The current period
     */
    private int currentPeriod = 0;

    public Period(String name, int numberOfPeriods, int lengthOfPeriod)
    {
        this.name = name;
        this.numberOfPeriods = numberOfPeriods;
        this.lengthOfPeriod = lengthOfPeriod;
    }

    /**
     * Gets the total number of periods
     *
     * @return total number of periods
     */
    public int getNumberOfPeriods() {
        return numberOfPeriods;
    }

    /**
     * Gets the length of each period
     *
     * @return length of periods
     */
    public int getLengthOfPeriod() {
        return lengthOfPeriod;
    }

    /**
     * Get the current period the game is in. The game is over when the current period is greater than the total number
     * of periods in the game.
     *
     * @return the current period the game is in
     */
    public int getCurrentPeriod()
    {
        return currentPeriod;
    }

    /**
     * Checks whether the game has started or not. A game has started after the current period exceeds 0
     *
     * @return whether the game has started
     */
    public boolean hasGameStarted()
    {
        return currentPeriod > 0;
    }

    /**
     * Checks if the game has started. A game has started if the current period IS NOT 0.
     *
     * @return whether the game has started
     */
    public boolean startGame()
    {
        if(currentPeriod == 0)
        {
            currentPeriod = 1;
            return true;
        }

        return false;
    }

    /**
     * Checks whether the game is over and returns the result. The game is over when the current period greater than
     * the number of periods.
     *
     * @return true if the game has ended
     */
    public boolean isGameOver()
    {
        return currentPeriod > numberOfPeriods;
    }

    /**
     * Increments the current period to the next period if possible. If the game has not started or the game is over the
     * current period will not change.
     *
     * @return whether the current period has changed
     */
    public boolean endCurrentPeriod()
    {
        if(!hasGameStarted()) return false;
        if(isGameOver()) return false;

        currentPeriod++;
        return true;
    }

    /**
     * Advances the game to the next period and returns whether the game is now over. If the game is already over then
     * the period is not changed and false is retruned
     *
     * @return whether the game has ended due to the change in period
     */
    public boolean nextPeriod()
    {
        if(currentPeriod < numberOfPeriods)
        {
            currentPeriod++;
            return true;
        }
        else if(currentPeriod == numberOfPeriods)
        {
            currentPeriod++;
        }

        return false;
    }

    /**
     * Gets the name of the period
     *
     * @return name of the period
     */
    public String getName()
    {
        return name;
    }
}
