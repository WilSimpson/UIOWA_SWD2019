import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.TreeMap;

public class ChangeComputation
{
    private static final DecimalFormat FORMATTER = new DecimalFormat("0.00");
    private Scanner scanner = new Scanner(System.in);
    private Drawer drawer = new Drawer();

    public static void main(String[] args)
    {
        ChangeComputation program = new ChangeComputation();

        int choice = 0;
        while(choice != 1 && choice != 2)
        {
            choice = program.getIntegerInput(
                    "Would you like to specify number of each currency given or total value of the currency given?",
                                    "\n1. Given numbers"
                                            +"\n2. Given each currency"
                                            +"\nYour choice: ");

            System.out.println("\n\n");
        }

        if(choice == 1)
        {
            program.runGivenNumbers();
        }
        else
        {
            program.runGivenCurrency();
        }
    }

    public void runGivenCurrency()
    {
        float itemPrice = getFloatInput("Enter the price of the item without any commas.",
                "Item price: $");

        System.out.println("\n\n");
        TreeMap<Currency, Integer> currencyGiven = Drawer.generateEmptyCurrencyMap();

        System.out.println("Enter amount of each currency given.");
        for(Currency c : currencyGiven.keySet())
        {
            currencyGiven.put(c, getIntegerInput(c+": \t"));
        }


        float f = Drawer.valueOfCurrencyGiven(currencyGiven);

        float change= f-itemPrice;

        System.out.println("\n\n");

        if(change < 0)
        {
            System.out.println("Not enough money given!");
        }
        else
        {
            System.out.println("Change to be given: ");
            Drawer.printMap(drawer.calculateChange(itemPrice, f));
            System.out.println("Total value: $"+formatFloat(change));
        }
    }


    public void runGivenNumbers()
    {
        float itemPrice = getFloatInput("Enter the price of the item without any commas.",
                "Item price: $");

        System.out.println("\n");
        float moneyGiven = getFloatInput("Enter the amount of money given",
                "Money given: $");

        System.out.println("\n\n");
        float change = moneyGiven - itemPrice;

        if(change < 0)
        {
            System.out.println("Not enough money was given to pay for the item!");
        }
        else
        {
            System.out.println("Change to be given: ");
            Drawer.printMap(drawer.calculateChange(itemPrice, moneyGiven));
            System.out.println("Total value: $"+formatFloat(change));
        }
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
