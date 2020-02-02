import java.util.Comparator;

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

    private final String name;
    private final float value;

    private static Comparator comparator = new Comparator<Currency>() {
        @Override
        public int compare(Currency c1, Currency c2) {
            return Float.compare(c2.getValue(), c1.getValue());
        }
    };

    private Currency(String name, float value)
    {
        this.name = name;
        this.value = value;
    }

    public static Comparator getComparator()
    {
        return comparator;
    }

    public float getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return name;
    }


}
