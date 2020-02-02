import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.TreeMap;

public class ChangeComputation
{
    private static final DecimalFormat FORMATTER = new DecimalFormat("0.00");
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        (new ChangeComputation()).run();
    }

    public void run()
    {
        System.out.println("Initializing drawer");
        Drawer drawer = new Drawer();

        drawer.printDrawerContents();
        System.out.println("\n\n");


        float itemPrice = getFloatInput("Give a positive price for the item: ",
                "Item price: $");

        drawer.setItemPrice(itemPrice);

        System.out.println("Start entering transactions!\n");

        int numberTransactions = 0;

        System.out.println("Transaction "+numberTransactions+1+":");
        TreeMap<Currency, Integer> currencyGiven = Drawer.generateEmptyCurrencyMap();

        System.out.println("Transaction "+numberTransactions+1+": Enter amount of each currency given.");
        for(Currency c : currencyGiven.keySet())
        {
            currencyGiven.put(c, getIntegerInput(c+": \t"));
        }

        drawer.processTransaction(currencyGiven);
    }

    private float getFloatInput(String messageHeader, String message)
    {
        System.out.println(messageHeader);
        float f = 0;

        while(f <= 0)
        {
            try
            {
                System.out.print(message);
                f = Float.parseFloat(scanner.nextLine());
            }
            catch(NumberFormatException e)
            {
                System.out.println("ERROR: MUST ENTER A NUMBER!");
            }
        }

        return formatFloat(f);
    }

    public float formatFloat(float f)
    {
        return Float.parseFloat(FORMATTER.format(f));
    }


    private int getIntegerInput(String message)
    {
        int i = -1;
        while(i <= -1)
        {
            try
            {
                System.out.print(message);
                i = Integer.parseInt(scanner.nextLine());
            }
            catch(NumberFormatException e)
            {
                System.out.println("ERROR: MUST ENTER A INTEGER!");
            }
        }

        return i;
    }

    private int getIntegerInput(String messageHeader, String message)
    {
        System.out.println(messageHeader);
        return getIntegerInput(message);
    }
}
