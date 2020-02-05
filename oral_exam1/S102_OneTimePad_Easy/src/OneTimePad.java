import java.util.Arrays;
import java.util.Scanner;

/**
 * A simple encrypter that shifts a message by any number through a predefined alphabet in the enctyper. This handles
 * gathering input from the user and printing the encrypted message to the screen.
 *
 * @author Wil Simpson
 */
public class OneTimePad
{
    /**
     * Scanner to get input from the console
     */
    private final Scanner SCANNER = new Scanner(System.in);

    /**
     * Encrypter used to encrypt messages
     */
    private final Encrypter ENCRYPTER = new Encrypter();

    /**
     * Input from the user
     */
    private String input = null;

    /**
     * Starts the program
     */
    public void run()
    {

        String message = askValidString("Enter a message to encrypt");
        int shiftSize = askValidInt("Size of shift");

        System.out.println("Encrypted message: "+ENCRYPTER.encryptMessage(message, shiftSize));

        if(askRunAgain())
        {
            System.out.println("#############################\n\n");
            run();
        }
    }

    /**
     * Keeps asking the user if the program should run again until a valid response is given.
     *
     * @return whether the user chose to run the program again
     */
    private boolean askRunAgain()
    {
        String[] validYs = {"y", "YES"};
        String[] validNs = {"N", "NO", "EXIT"};

        return containsString(askValidString("Run again? [(Y) / N]", validYs, validNs), validYs);
    }

    /**
     * Keeps sending the user a given string and asks for their input until one of the valid strings is given. If there
     * are no valid strings then any string entered is valid. The string is not case sensitive.
     *
     * @param message message to display to the user that tells the user what to input
     * @param validStrings valid inputs. not case sensitive
     * @return valid string given in console
     */
    private  String askValidString(String message, String[]... validStrings)
    {
        System.out.print(message+": ");


        //Converts all valid strings to uppercase
        validStrings = Arrays.stream(validStrings)
                .map(s ->
                        Arrays.stream(s)
                                .map(String::toUpperCase)
                                .toArray(String[]::new))
                .toArray(String[][]::new);

        synchronized(this)
        {
            input = SCANNER.nextLine().toUpperCase();

            //Check if input needs to be validated
            if(validStrings.length > 0)
            {
                //Check if the input is one of the given valid strings
                if(Arrays.stream(validStrings).anyMatch(s -> containsString(input, s)))
                {
                    return input;
                }

                askValidString(message, validStrings);
            }
        }

        return input;
    }

    /**
     * Checks if a message contains any string in a given array
     *
     * @param message message to check
     * @param strings string to check for
     * @return whether the message contains any of the strings
     */
    private boolean containsString(String message, String[] strings)
    {
        return Arrays.stream(strings).anyMatch(s -> s.contains(message.toUpperCase()));
    }

    /**
     * Keeps sending the user a given message and asking for an int from the console with a given message until a valid
     * one is given. If there are no required valid ints then any int entered is returned.
     *
     * @param message message to display to the user that tells the user what to input
     * @param validInts valid ints. no valid ints means any int is acceptable
     * @return int given in console
     */
    private int askValidInt(String message, int[]... validInts)
    {
        System.out.print(message+": ");

        try
        {
            return Integer.parseInt(SCANNER.nextLine());
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("##############################\n");
            return askValidInt(message);
        }
    }
}
