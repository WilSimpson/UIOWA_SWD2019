import java.util.*;

public class Menu
{
    private Scanner scanner = new Scanner(System.in);
    private String header;
    private Map<String, Object> options = new LinkedHashMap<>();

    public Menu(String header)
    {
        this.header = header;
    }

    public void addMenuOption(String name, Object object)
    {
        options.put(name, object);
    }

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

    public int getNumberOptions()
    {
        return options.keySet().size();
    }

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

    private void printMenu()
    {
        printlnMessage(header+":");
        for(int i=0; i<options.size(); i++)
        {
            printlnMessage(i+1+". "+options.keySet().toArray()[i]);
        }
        printMessage("Enter Choice: ");
    }

    private void printlnMessage(String message)
    {
        System.out.println(message);
    }

    private void printMessage(String message)
    {
        System.out.print(message);
    }
}
