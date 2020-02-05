import java.util.*;

/**
 * A menu consists of a header and options. This class handles printing the choices to the screen as well as retrieving
 * the answer. If an incorrect value is entered this class handles asking again for a valid answer.
 *
 * A better design for the options map would be to have a list of a class MenuOptions which is an abstract class. Which
 * would be implemented by ScoringMenuOption and GameMenuOption. These classes would have a scoring method with a team
 * as well as a game attached to them respectively. Then responding with indexes of an array/list/map would not be
 * needed. Returning a MenuOption would be used instead. Casting the MenuOption to the correct object then getting their
 * respective values.
 *
 * @author Wil Simpson
 */
public class Menu
{
    /**
     * Scanner used to get input from the console
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Header or title to print for the menu. Also can be see as the name for the menu
     */
    private String header;

    /**
     * Contains all the options for the game. The key is the name of the option and the value is the object that is
     * attached to the option
     */
    private Map<String, Object> options = new LinkedHashMap<>();

    /**
     * Creates a menu given a header
     *
     * @param header header for the menu
     */
    public Menu(String header)
    {
        this.header = header;
    }

    /**
     * Adds an option to the menu
     *
     * @param name name of the option
     * @param object object that is attached to the option
     */
    public void addMenuOption(String name, Object object)
    {
        options.put(name, object);
    }

    /**
     * Prints the menu to the user and gets the index for the chosen option. Keeps asking until a valid option is
     * chosen.
     *
     * @return int input given
     */
    public int getChoice()
    {
        printMenu();
        int input = getUserInputChoice();

        if(input < 0 || input > options.size()-1)
        {
            printlnMessage("########################################\n"
                          +"ERROR! Entered Choice was not an option!"
                        +"\n########################################\n\n\n");
            return getChoice();
        }

        printlnMessage("#######################\n\n");
        return input;
    }

    /**
     * Gets the total number of options for the menu
     *
     * @return number of options for the menu
     */
    public int getNumberOptions()
    {
        return options.keySet().size();
    }

    /**
     * Gets an integer from the console. If the input is invalid, a value of -1 is returned.
     *
     * @return int given from console
     */
    private int getUserInputChoice()
    {
        try
        {
            return Integer.valueOf(scanner.nextLine()) - 1;
        }
        catch(NumberFormatException ex)
        {
            return -1;
        }
    }

    /**
     * Prints the menu to the console and asks for input. That is it prints the header. all of the options, and the
     * message asking to enter a choice.
     */
    private void printMenu()
    {
        printlnMessage(header+":");
        for(int i=0; i<options.size(); i++)
        {
            printlnMessage(i+1+". "+options.keySet().toArray()[i]);
        }
        printMessage("Enter Choice: ");
    }

    /**
     * Prints a message to the console followed by a new line.
     *
     * @param message message to print to the console
     */
    private void printlnMessage(String message)
    {
        System.out.println(message);
    }

    /**
     * Prints a message to the console
     *
     * @param message message to print to the console
     */
    private void printMessage(String message)
    {
        System.out.print(message);
    }
}
