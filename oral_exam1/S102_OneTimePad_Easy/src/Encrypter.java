/**
 * The encrypter creates a known alphabet of size 26 containing the letters A through Z.
 * Use the encryptLetter to encrypt a single char or a whole message through the encryptMessage method.
 *
 * @author Wil Simpson
 */
public class Encrypter
{
    /**
     * List of all capital letters from A to Z in order.
     */
    private static final char alphabet[] =
            {
                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
            };

    /**
     * Shifts the given char by the given shift size. Only encrypts letter if it's apart of the alphabet.
     *
     * @param c Current char to shift
     * @param shiftSize amount to shift char by
     * @return new shifted char value
     */
    public char encryptLetter(char c, int shiftSize)
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
    public String encryptMessage(String message, int shiftSize)
    {
        char[] originalMessage = message.toUpperCase().toCharArray();
        String encryptedMessage = "";

        for(int i=0; i<originalMessage.length; i++)
        {
            encryptedMessage += encryptLetter(originalMessage[i], shiftSize);
        }

        return encryptedMessage;
    }

    /**
     * Checks if the given char is in the alphabet.
     *
     * @param c char to check
     * @return whether the char is in the alphabet
     */
    private boolean shouldEncryptChar(char c)
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
    private int letterToIndex(char letter)
    {
        for(int i=0; i<alphabet.length; i++)
        {
            if(indexToLetter(i) == Character.toUpperCase(letter)) return i;
        }

        return -1;
    }

    /**
     * Expects an index inclusively between 0 and 25 and converts that into a letter given the alphabet array. If an
     * invalid index is given a value of 0 is returned.
     *
     * @param index index of letter in array
     * @return letter in array at the given index
     */
    private char indexToLetter(int index)
    {
        if(index < 0 || index > alphabet.length) return 0;

        return alphabet[index];
    }
}
