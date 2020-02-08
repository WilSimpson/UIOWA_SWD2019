import java.util.Scanner;

/**
 * Simulates transaction with a drawer. After calling the run method the simulation continues until the user chooses to
 * no longer simulate transactions.
 *
 * @author Wil Simpson
 */
public class ChangeSimulation
{

    /**
     * Scanner to gather input from console
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Whether to keep accepting inputs from the user or end the program
     */
    boolean continueRunning = true;

    /**
     * Gets user information from the console required to run the simulation such as the item price then starts the
     * simulation
     */
    public void run()
    {
        //Create a new drawer
        System.out.println("Initializing drawer");
        Drawer drawer = new Drawer();

        drawer.printDrawerContents();
        System.out.println("\n\n");

        //Get the price of the item
        float itemPrice = getFloatInput("Give a positive price for the item: ",
                "Item price: $");
        drawer.setItemPrice(itemPrice);

        System.out.println("\nStart entering transactions!");

        runSimulation(drawer);
    }

    /**
     * Run the simulation given a drawer.
     *
     * @param drawer drawer to run simulation on
     */
    public void runSimulation(Drawer drawer)
    {
        int numberTransactions = 1;

        //Main loops
        while(continueRunning)
        {
            Currencies currencyGiven = new Currencies();

            //Show the drawer
            System.out.println("\n\nCurrent drawer:");
            drawer.printDrawerContents();

            //Get user payment
            System.out.println("\n\nTransaction "+numberTransactions+": Enter amount of each currency given.");
            for(Currency c : Currency.values())
            {
                currencyGiven.setCurrency(c, getIntegerInput(c+": \t"));
            }

            //Process the transaction
            try
            {
                Currencies change = drawer.processTransaction(currencyGiven);
                System.out.println("Transaction processed! Given change: $"+change.getValue());
                change.print();

                //Show the drawer after the transaction
                System.out.println("\n\nDrawer after transaction:");
                drawer.printDrawerContents();
            }
            //If transaction failed tell the user why
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

            ++numberTransactions;

            //Check if the user wants to continue entering transactions
            continueRunning = askToContinue();
        }
    }


    /**
     * Asks the user if they would like to continue running the program and returns true if they want to continue.
     *
     * @return whether they console input responded with yes
     */
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

    /**
     * Prints the message header then the message to the console. Then gets the console input. The message will be sent
     * and user input will be taken until a valid float is given.
     *
     * @param messageHeader message that gives the other message more context
     * @param message message asking what value the user should input
     * @return float input from console
     */
    private float getFloatInput(String messageHeader, String message)
    {
        System.out.println(messageHeader);
        Float f = null;

        while(f == null)
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

    /**
     * Prints the message to the console and gets the user input to the message. The message will be sent and user input
     * will be taken until a valid int is given.
     *
     * @param message message to show the console
     * @return int input from console
     */
    private int getIntegerInput(String message)
    {
        Integer i = null;

        while(i == null)
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

    /**
     * Prints the header message, then the message to the console and gets the user input to the message. The message
     * will be sent and user input will be taken until a valid int is given.
     *
     * @param messageHeader message that gives the other message more context
     * @param message message to show the console
     * @return int input from console
     */
    private int getIntegerInput(String messageHeader, String message)
    {
        System.out.println(messageHeader);
        return getIntegerInput(message);
    }
}
