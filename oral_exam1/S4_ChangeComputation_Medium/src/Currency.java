import java.util.Comparator;

/**
 * An enum simulating currency consisting of a name and a value. All bills less than $20 and all 4 coins are used.
 *
 * @author Wil Simpson
 */
public enum Currency
{
    TWENTY_BILL("$20", 20f),
    TEN_BILL("$10", 10f),
    FIVE_BILL("$5", 5f),
    ONE_BILL("$1", 1f),
    QUARTER("25¢", 0.25f),
    DIME("10¢", 0.1f),
    NICKEL("5¢", 0.05f),
    PENNY("1¢", 0.01f);

    /**
     * Name of the currency
     */
    private final String name;

    /**
     * Value of the currency
     */
    private final float value;

    /**
     * How the currencies should be compared
     */
    private static final Comparator comparator = new Comparator<Currency>() {
        @Override
        public int compare(Currency c1, Currency c2) {
            return Float.compare(c2.getValue(), c1.getValue());
        }
    };

    /**
     * Creates a currency given a name and value
     *
     * @param name name of the currency
     * @param value value of the currency
     */
    Currency(String name, float value)
    {
        this.name = name;
        this.value = value;
    }

    /**
     * Gets how currencies should be compared
     *
     * @return comparator for the currencies
     */
    public static Comparator getComparator()
    {
        return comparator;
    }

    /**
     * Get the value of the currency
     *
     * @return value of the currency
     */
    public float getValue()
    {
        return value;
    }

    /**
     * Get the name of the currency
     *
     * @return name of the currency
     */
    @Override
    public String toString()
    {
        return name;
    }


}
