import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Drawer
{
    private Map<Currency, Integer> drawer = new HashMap<>();

    /**
     * Initialize the draw to have unlimited -1 of each currency meaning there is unlimited of the bill.
     */
    public Drawer()
    {
        for(Currency currency : Currency.values())
        {
            drawer.put(currency, -1);
        }

        Map<Currency, Integer> change = generateEmptyCurrencyMap();

        printMap(change);

        change.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach((key) -> key.getValue());

        printMap(change);
    }

    /**
     * Initialize the draw with the given amount of currency
     *
     * @param drawer drawer to use
     */
    public Drawer(Map<Currency, Integer> drawer)
    {
        this.drawer = drawer;
    }

    public Map<Currency, Integer> calculateChange(float itemCost, float moneyGiven)
    {
        if(moneyGiven < itemCost) return null;

        Map<Currency, Integer> change = new LinkedHashMap<>();

        printMap(change);

        change.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach((key) -> key.getValue());

        printMap(change);

        return change;
    }

    public static Map<Currency, Integer> generateEmptyCurrencyMap()
    {
        Map<Currency, Integer> drawer = new HashMap<>();
        for(Currency currency : Currency.values())
        {
            drawer.put(currency, 0);
        }

        return drawer;
    }

    public void printMap(Map<Currency, Integer> map)
    {
        for(int i=0; i<map.keySet().size(); i++)
        {
            System.out.print(map.keySet().toArray()[i].toString()+", ");
        }
        System.out.println();
    }
}
