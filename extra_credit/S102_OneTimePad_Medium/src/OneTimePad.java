import java.util.*;

/**
 * A simple cypher that shifts a message by any number given an alphabet. The alphabet is given by the alphabet array
 * and intends to be used with A-Z OR A-Z + 0-9
 *
 * @author Wil Simpson
 * @todo complete and review javadocs
 */
public class OneTimePad
{
    /**
     * An alphabet of all chars that can be encrypted. Contains a list of all capital letters from A to Z in order
     * directly followed by 0 through 9 in order.
     */
    private static final char alphabet[] =
            {
                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
            };//'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        //};

    /**
     * Start of the program. No command line arguments are needed. Example given is the same one
     *
     * @param args command line arguments.
     */
    public static void main(String[] args)
    {
        String inputMessage;
        String shiftsMessage = "";
        String encryptedMessage = "";
        int requiredNumberOfShifts = 0;
        int numberShiftsGiven = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a message: ");
        inputMessage = scanner.nextLine().toUpperCase();

        //This calculates the number of shifts required to get from the user. The program currently requires there to be
        //a shift for every char in the message that is in the alphabet defined above. However this can be set to any
        //arbitrary number and if the number of shifts is less than the number of possible shifts then the shifts will
        //repeat in order as necessary. If the number is greater then the shifts will be precessed in order until the
        //end of the input message and the remainder ignored
        requiredNumberOfShifts = possibleNumberOfShifts(inputMessage);

        //Get the minimum required number of shifts.
        do
        {
            System.out.print("Enter a number separated by commas for each char to be"
                    +" encrypted (Given: "+numberShiftsGiven+"/"+ requiredNumberOfShifts+"): ");

            shiftsMessage += scanner.nextLine();

            numberShiftsGiven = shiftsMessage.split(",").length;
        }
        while(requiredNumberOfShifts > numberShiftsGiven);


        //Turn the shift strings into an array
        String[] shiftStrings = shiftsMessage.split(",");
        int[] shifts = new int[shiftStrings.length];
        for(int i=0; i<shifts.length; i++)
        {
            shifts[i] = Integer.valueOf(shiftStrings[i]);
        }

        encryptedMessage = encryptMessage(inputMessage, shifts);

        System.out.println(encryptedMessage);
    }

    /**
     * Calculates the number of shifts that can be done on a particular message. One shift can be done for each char in
     * the message that is in the defined alphabet.
     *
     * @param inputMessage message to check
     * @return number of shifts needed to fully encrypt the input message
     */
    public static int possibleNumberOfShifts(String inputMessage)
    {
        int requiredNumberOfShifts = 0;
        for(int i=0; i<inputMessage.length(); i++)
        {
            char currentChar = inputMessage.charAt(i);
            if(canEncrypt(currentChar))
            {
                requiredNumberOfShifts++;
            }
        }

        return requiredNumberOfShifts;
    }

    /**
     * If the given char is in the alphabet then this method will return the char shiftSize indexes given the defined
     * alphabet. If the char is not in the alphabet then the original char will be returned.
     *
     * @param c char to encrypt
     * @param shiftSize number of shifts in alphabet
     * @return encrypted char
     */
    public static char encryptLetter(char c, int shiftSize)
    {
        if(!canEncrypt(c)) return c;

        int newIndex = (letterToIndex(c)+shiftSize) % alphabet.length;
        return indexToLetter(newIndex);
    }

    /**
     * Encrypts the given message by shifting each letter by the given shift size. Each index in the shifts represents
     * the index in the message that will be shifted. Letters will wrap from the end of the alphabet to the start. All messages will be converted to uppercase.
     *
     * @param message message to encrypt
     * @param shifts shifts at each position
     * @return encrypted message
     */
    public static String encryptMessage(String message, int[] shifts)
    {
        String encryptedMessage = "";

        for(int i=0; i<message.length(); i++)
        {
            int currentShift = shifts[i%shifts.length];
            char currentChar = message.charAt(i);

            if(currentShift > 0)
            {
                currentChar = encryptLetter(currentChar, currentShift);
            }

            encryptedMessage += currentChar;
        }

        return encryptedMessage;
    }

    /**
     * Checks if the given char is in the alphabet.
     *
     * @param c char to check
     * @return whether the char is in the alphabet
     */
    private static boolean canEncrypt(char c)
    {
        for(char currentChar : alphabet)
        {
            if(currentChar == c) return true;
        }

        return false;
    }

    /**
     * Expects a char letter from A-Z and converts it to an index in the alphabet array. If an invalid char is given an
     * index of -1 will be returned.
     *
     * @param letter letter to search for in the array
     * @return index of the letter in the array
     */
    public static int letterToIndex(char letter)
    {
        for(int i=0; i<alphabet.length; i++)
        {
            if(indexToLetter(i) == Character.toUpperCase(letter)) return i;
        }

        return -1;
    }

    /**
     * Expects an index inclusively between 0 and 35 and converts that into a letter given the alphabet array. If an
     * invalid index is given a value of 0 is returned.
     *
     * @param index index of letter in array
     * @return letter in array at the given index
     */
    public static char indexToLetter(int index)
    {
        if(index < 0 || index > alphabet.length) return 0;

        return alphabet[index];
    }


}
