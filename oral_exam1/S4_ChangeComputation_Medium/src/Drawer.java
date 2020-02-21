import java.text.DecimalFormat;

/**
 * A cash drawer for a business. Has a currencies and an item price as long with methods to process a transaction.
 *
 * @author Wil Simpson
 */
public class Drawer
{
    /**
     * Decimal format that will be used
     */
    private static final DecimalFormat FORMATTER = new DecimalFormat("0.00");

    /**
     * Sorted map of every currency and the amount of each
     */
    private Currencies drawerCurrencies;

    /**
     * Price of the item in the drawer
     */
    private float itemPrice;

    /**
     * Initialize the draw to have a random amount of each currency between 0 and 15 with an item price of $1
     */
    public Drawer()
    {
        this(1.0f);
    }

    /**
     * Initialize the draw to have a random amount of each currency between 0 and 15 and sets the item price to the
     * given item price
     *
     * @param itemPrice price of the item in the drawer
     */
    public Drawer(float itemPrice)
    {
        this.itemPrice = itemPrice;
        drawerCurrencies = (new Currencies()).randomize(0, 15);
    }

    /**
     * Prints the contents of the drawer
     */
    public void printDrawerContents()
    {
        drawerCurrencies.print();
    }

    /**
     * Sets the price of the item
     *
     * @param itemPrice new price for the item
     */
    public void setItemPrice(float itemPrice)
    {
        this.itemPrice = itemPrice;
    }

    /**
     * Processes a transaction with the given money. If the given money is not enough or the drawer does not have the
     * correct change then the transaction will not be processed, the drawer will not be effected and a
     * TransactionException will be thrown
     *
     * @param moneyGiven currencies given to pay for the item
     * @return currencies given as change
     * @throws TransactionException throws an error if a transaction fails for some reason
     */
    public Currencies processTransaction(Currencies moneyGiven)
            throws TransactionException
    {
        //Total value of the money given and change rounded to the nearest cent
        float moneyValue = moneyGiven.getValue();
        float changeNeeded = formatFloat(moneyValue - itemPrice);

        //Change that will be given
        Currencies changeCurrencies = new Currencies();

        //Check if enough money was given, if not throw an exception
        if(changeNeeded < 0)
        {
            throw new TransactionException(TransactionResult.INSUFFICIENT_FUNDS);
        }
        //If exact change was given then an empty change map will be given
        else if(changeNeeded == 0)
        {
            return changeCurrencies;
        }

        //Combine the money given and the current drawer together
        Currencies combinedDrawer = new Currencies(moneyGiven, drawerCurrencies);

        //Find change until the needed change is 0
        while(changeNeeded > 0)
        {
            Currency currentCurrency = combinedDrawer.getBiggestCurrency(changeNeeded);

            if(currentCurrency == null) throw new TransactionException(TransactionResult.INSUFFICIENT_CHANGE);

            changeCurrencies.incrementCurrency(currentCurrency);
            combinedDrawer.decrementCurrency(currentCurrency);
            changeNeeded -= currentCurrency.getValue();
            changeNeeded = formatFloat(changeNeeded);
        }

        drawerCurrencies = combinedDrawer;

        return changeCurrencies;
    }

    /**
     * Rounds the given float to the nearest cent
     *
     * @param f number to format
     * @return float rounded to the nearest cent
     */
    public static float formatFloat(float f)
    {
        return Float.parseFloat(FORMATTER.format(f));
    }
}
