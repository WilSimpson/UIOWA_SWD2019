/**
 * A simple cypher that shifts a message by any number given an alphabet. The alphabet is given by the alphabet array
 * and intends to be used with A-Z OR A-Z + 0-9
 *
 * @author Wil Simpson
 */
public class OneTimePad
{
    /**
     * List of all capital letters from A to Z in order directly followed by 0 through 9 in order.
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
        String message = "MEET ME AT THREE.";

        System.out.println(message);
        System.out.println(encryptMessage(message, 13));
    }

    /**
     * Shifts the given char by the given shift size. Only encrypts letter if it's apart of the alphabet.
     *
     * @param c Current char to shift
     * @param shiftSize amount to shift char by
     * @return new shifted char value
     */
    public static char encryptLetter(char c, int shiftSize)
    {
        if(!shouldEncryptChar(c)) return c;

        int newIndex = (letterToIndex(c)+shiftSize) % alphabet.length;
        return indexToLetter(newIndex);
    }

    /**
     * Encrypts the given message by shifting each letter by the given shift size. If the number is larger than the
     * alphabet size the shiftSize will wrap back to 0. This encryption keeps the original spacings. All letters will
     * be converted to their uppercase equivalent if they are not already in uppercase.
     *
     * @param message message to encrypt
     * @param shiftSize amount to shift each character
     * @return encrypted message
     */
    public static String encryptMessage(String message, int shiftSize)
    {
        char[] originalMessage = message.toUpperCase().toCharArray();
        String encryptedMessage = "";

        for(int i=0; i<originalMessage.length; i++)
        {
            encryptLetter(originalMessage[i], shiftSize);
        }

        return encryptedMessage;
    }

    /**
     * Checks if the given char is in the alphabet.
     *
     * @param c char to check
     * @return whether the char is in the alphabet
     */
    private static boolean shouldEncryptChar(char c)
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