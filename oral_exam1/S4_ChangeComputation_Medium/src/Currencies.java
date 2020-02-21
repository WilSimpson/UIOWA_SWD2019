import java.util.TreeMap;

/**
 * A map of every Currency and a number representing how much of each.
 *
 * @author Wil Simpson
 */
public class Currencies
{
    /**
     * Sorted map of every currency and the amount of each sorted from biggest currency to smallest currency
     */
    private final TreeMap<Currency, Integer> currencies;

    /**
     * Creates an new currencies given any number of other currencies. If none are given then the amount will be
     * initialized to 0
     *
     * @param currenciesArray currencies to combine
     */
    public Currencies(Currencies... currenciesArray)
    {
        currencies = new TreeMap<>(Currency.getComparator());
        for(Currency currentCurrency : Currency.values())
        {
            int amount = 0;
            for(Currencies currentCurrencies : currenciesArray)
            {
                amount += currentCurrencies.getAmount(currentCurrency);
            }

            currencies.put(currentCurrency, amount);
        }
    }

    /**
     * Gets the amount of a given currency
     *
     * @param currency currency to give amount of
     * @return amount of given currency
     */
    public int getAmount(Currency currency)
    {
        return currencies.get(currency);
    }

    /**
     * Decrements the current amount of currency by one if the current amount is greater than 0.
     *
     * @param c currency to decrement
     * @throws TransactionException throws an insufficient change exception if the decrement will cause the amount to
     *                              be less than 0
     */
    public void decrementCurrency(Currency c) throws TransactionException
    {
        int currentAmount = getAmount(c);

        if(currentAmount == 0) throw new TransactionException(TransactionResult.INSUFFICIENT_CHANGE);

        setCurrency(c, currentAmount-1);
    }

    /**
     * Increments the current amount of currency by one
     *
     * @param c currency to increment
     */
    public void incrementCurrency(Currency c)
    {
        setCurrency(c, getAmount(c)+1);
    }

    /**
     * Sets the given currency to the given amount
     *
     * @param c currency to set amount of
     * @param amount amount to set to
     */
    public void setCurrency(Currency c, int amount)
    {
        currencies.put(c, amount);
    }

    /**
     * Gets the value of all of the currencies added together
     *
     * @return value of all currencies
     */
    public float getValue()
    {
        float value = 0;
        for(Currency c : currencies.keySet())
        {
            value += c.getValue()*currencies.get(c);
        }

        return value;
    }

    /**
     * Gets the biggest value currency with an amount of greater than zero. If there isn't one then null will be
     * returned
     *
     * @param value value of change needed
     * @return biggest possible currency that is less than the given value
     */
    public Currency getBiggestCurrency(float value)
    {
        for(Currency c : Currency.values())
        {
            if(getAmount(c) > 0 && c.getValue() <= value)
            {
                return c;
            }
        }

        return null;
    }

    /**
     * Sets all the currencies to 0 and returns a reference to itself
     *
     * @return itself
     */
    public Currencies reset()
    {
        for(Currency currency : Currency.values())
        {
            currencies.put(currency, 0);
        }

        return this;
    }

    /**
     * Randomizes the amount of each currency inclusively between the given minimum and maximum
     *
     * @param minimum minimum amount to set to
     * @param maximum maximum amount to set to
     * @return itself
     */
    public Currencies randomize(int minimum, int maximum)
    {
        for(Currency currency : Currency.values())
        {
            currencies.put(currency, (int) (Math.random()*(maximum-minimum+1)+minimum));
        }

        return this;
    }

    /**
     * Prints all currencies and their amount to a new line in the console
     */
    public void print()
    {
        System.out.println(this.toString()
                .replaceAll("=", ": \t")
                .replaceAll("; ", "\n")
                .replaceAll(";", ""));
    }

    /**
     * Human readable string of all the currencies and the amount for each
     *
     * @return string containing all currencies and the amount of each
     */
    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder();

        for(Currency c : Currency.values())
        {
            string.append(c).append("=").append(getAmount(c)).append("; ");
        }

        return string.substring(0, string.length()-1);
    }

}
