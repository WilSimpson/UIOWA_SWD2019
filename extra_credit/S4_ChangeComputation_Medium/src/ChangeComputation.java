import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.TreeMap;

public class ChangeComputation
{

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        (new ChangeComputation()).run();
        System.out.println("\n\nBYE!");
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

        System.out.println("\nStart entering transactions!");

        boolean continueRunning = true;

        while(continueRunning)
        {
            int numberTransactions = 0;

            TreeMap<Currency, Integer> currencyGiven = Drawer.generateEmptyCurrencyMap();

            System.out.println("\n\nCurrent drawer:");
            drawer.printDrawerContents();

            System.out.println("\n\nTransaction "+numberTransactions+1+": Enter amount of each currency given.");
            for(Currency c : currencyGiven.keySet())
            {
                currencyGiven.put(c, getIntegerInput(c+": \t"));
            }

            try
            {
                TreeMap<Currency, Integer> change = drawer.processTransaction(currencyGiven);
                System.out.println("Transaction processed!");
            }
            catch(TransactionException ex)
            {
                switch(ex.getResult())
                {
                    case INSUFFICIENT_CHANGE:
                        System.out.println("Unable to process the transaction due to insufficient change in the drawer!" +
                            "\nThe transaction has been canceled and has not effected the drawer.");
                        break;

                    case INSUFFICIENT_FUNDS:
                        System.out.println("Unable to processes transaction due to insufficient funds given." +
                            "\nThe transaction has been canceled and has not effected the drawer.");
                        break;

                    default:
                        System.out.println("UNKNOWN ERROR! Transaction has been canceled!");
                }
            }


            System.out.println("\n\nCurrent drawer:");
            drawer.printDrawerContents();

            continueRunning = askToContinue();


        }

    }

    private boolean askToContinue()
    {

        int response;
        do
        {
            response = getIntegerInput("\n\nWould you like to continue?",
                    "1. Yes" +
                            "\n2. No" +
                            "\nResponse: ");
        }
        while(response != 1 && response != 2);

        return response == 1;
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

        return Drawer.formatFloat(f);
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
