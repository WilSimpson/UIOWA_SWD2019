
import java.util.Map;
import java.util.TreeMap;

public class Drawer
{
    private TreeMap<Currency, Integer> drawer = new TreeMap<>();

    /**
     * Initialize the draw to have unlimited -1 of each currency meaning there is unlimited of the bill.
     */
    public Drawer()
    {
        for(Currency currency : Currency.values())
        {
            drawer.put(currency, -1);
        }
    }

    /**
     * Initialize the draw with the given amount of currency
     *
     * @param drawer drawer to use
     */
    public Drawer(TreeMap<Currency, Integer> drawer)
    {
        this.drawer = drawer;
    }

    public TreeMap<Currency, Integer> calculateChange(float itemCost, float moneyGiven)
    {
        if(moneyGiven < itemCost) return null;

        float change = moneyGiven - itemCost;
        TreeMap<Currency, Integer> changeMap = generateEmptyCurrencyMap();

        Currency biggestCurrency = getBiggestCurrency(change, changeMap);
        while(biggestCurrency != null)
        {
            changeMap.put(biggestCurrency, changeMap.get(biggestCurrency)+1);
            change -= biggestCurrency.getValue();

            biggestCurrency = getBiggestCurrency(change, changeMap);
        }

        return changeMap;
    }

    public Currency getBiggestCurrency(float change, TreeMap<Currency, Integer> changeMap)
    {
        for(Currency c : changeMap.keySet())
        {
            if(c.getValue() <= change)
            {
                return c;
            }
        }

        return null;
    }

    public static TreeMap<Currency, Integer> generateEmptyCurrencyMap()
    {
        TreeMap<Currency, Integer> drawer = new TreeMap<>(Currency.getComparator());
        for(Currency currency : Currency.values())
        {
            drawer.put(currency, 0);
        }

        return drawer;
    }

    public static void printMap(TreeMap<Currency, Integer> map)
    {
        for(Currency c : map.keySet())
        {
            System.out.println(c+": \t"+map.get(c));
        }
    }

    public static float valueOfCurrencyGiven(TreeMap<Currency, Integer> currencyGiven)
    {
        float value = 0;
        for(Currency c : currencyGiven.keySet())
        {
            value += c.getValue()*currencyGiven.get(c);
        }

        return value;
    }
}
