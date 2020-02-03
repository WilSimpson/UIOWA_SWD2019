import java.text.DecimalFormat;
import java.util.TreeMap;

public class Drawer
{
    private static final DecimalFormat FORMATTER = new DecimalFormat("0.00");

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
    }

    public void printDrawerContents()
    {
        printMap(drawer);
    }

    public void setItemPrice(float itemPrice)
    {
        this.itemPrice = itemPrice;
    }

    public TreeMap<Currency, Integer> processTransaction(TreeMap<Currency, Integer> moneyGiven) throws TransactionException
    {
        float moneyValue = valueOfCurrencyMap(moneyGiven);
        TreeMap<Currency, Integer> changeMap = generateEmptyCurrencyMap();

        if(moneyValue < itemPrice)
        {
            throw new TransactionException(TransactionResult.INSUFFICIENT_FUNDS);
        }

        float change = moneyValue - itemPrice;
        TreeMap<Currency, Integer> newDrawer = combineDrawers(moneyGiven, drawer);

        Currency currentCurrency = getBiggestCurrency(change, newDrawer);
        while(currentCurrency != null)
        {
            changeCurrency(changeMap, currentCurrency, 1);
            changeCurrency(newDrawer, currentCurrency, -1);
            change -= currentCurrency.getValue();

            currentCurrency = getBiggestCurrency(change, newDrawer);
        }

        change = formatFloat(change);

        if(change > 0)
        {
            throw new TransactionException(TransactionResult.INSUFFICIENT_CHANGE);
        }

        drawer = newDrawer;

        return changeMap;
    }

    private void changeCurrency(TreeMap<Currency, Integer> drawer, Currency curreny, int amount)
    {
        drawer.put(curreny, drawer.get(curreny)+amount);
    }

    private TreeMap<Currency, Integer> combineDrawers(TreeMap<Currency, Integer> drawer1, TreeMap<Currency, Integer> drawer2)
    {
        TreeMap<Currency, Integer> newDrawer = Drawer.generateEmptyCurrencyMap();

        for(Currency c : newDrawer.keySet())
        {
            newDrawer.put(c, drawer1.get(c)+drawer2.get(c));
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

    public static float valueOfCurrencyMap(TreeMap<Currency, Integer> currencyGiven)
    {
        float value = 0;
        for(Currency c : currencyGiven.keySet())
        {
            value += c.getValue()*currencyGiven.get(c);
        }

        return value;
    }

    public static float formatFloat(float f)
    {
        return Float.parseFloat(FORMATTER.format(f));
    }
}
