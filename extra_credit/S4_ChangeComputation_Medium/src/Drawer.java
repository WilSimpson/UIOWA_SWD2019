
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Drawer
{
    private TreeMap<Currency, Integer> drawer = new TreeMap<>();

    private float itemPrice;

    /**
     * Initialize the draw to have unlimited -1 of each currency meaning there is unlimited of the bill.
     */
    public Drawer()
    {
        for(Currency currency : Currency.values())
        {
            drawer.put(currency, (int) (Math.random()*(15+1)));
        }

        printMap(drawer);
    }

    public void printDrawerContents()
    {
        printMap(drawer);
    }

    public void setItemPrice(float itemPrice)
    {
        this.itemPrice = itemPrice;
    }

    public boolean processTransaction(TreeMap<Currency, Integer> moneyGiven)
    {
        float moneyValue = valueOfCurrencyGiven(moneyGiven);
        if(moneyValue < itemPrice)
        {
            System.out.println("Not enough money given to pay for the item!");
            return false;
        }

        TreeMap<Currency, Integer> newDrawer = combineDrawers(moneyGiven, drawer);



        return true;
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

    private TreeMap<Currency, Integer> combineDrawers(TreeMap<Currency, Integer> drawer1, TreeMap<Currency, Integer> drawer2)
    {
        TreeMap<Currency, Integer> newDrawer = Drawer.generateEmptyCurrencyMap();

        for(Currency c : newDrawer.keySet())
        {
            drawer.put(c, drawer1.get(c)+drawer2.get(c));
        }

        return newDrawer;
    }

    private Currency getBiggestCurrency(float change, TreeMap<Currency, Integer> changeMap)
    {
        for(Currency c : changeMap.keySet())
        {
            if(changeMap.get(c) > 0
                    && c.getValue() <= change)
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

    private static void printMap(TreeMap<Currency, Integer> map)
    {
        for(Currency c : map.keySet())
        {
            System.out.println(c+": \t"+map.get(c));
        }
    }

    private float valueOfCurrencyGiven(TreeMap<Currency, Integer> currencyGiven)
    {
        float value = 0;
        for(Currency c : currencyGiven.keySet())
        {
            value += c.getValue()*currencyGiven.get(c);
        }

        return value;
    }
}
