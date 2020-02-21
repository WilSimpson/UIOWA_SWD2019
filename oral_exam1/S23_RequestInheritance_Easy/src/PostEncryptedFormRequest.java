import java.util.UUID;

/**
 * Represents a post encrypted form requests and requires a uuid, ip, form that is requested adn the encryption scheme
 * to be created
 *
 * @author Wil Simpson
 */
public class PostEncryptedFormRequest extends PostFormRequest implements Encrypted
{
    /**
     * Total number of PostEncryptedFormRequest's made
     */
    private static int count = 0;

    /**
     * Encryption scheme of the request
     */
    private String encryptionScheme;

    /**
     * Creates a encrypted post form request
     *
     * @param uuid uuid of request
     * @param ip ip for request
     * @param form form for request
     * @param encryptionScheme encryption scheme for the request
     */
    public PostEncryptedFormRequest(UUID uuid, String ip, Form form, String encryptionScheme)
    {
        super(uuid, ip, form);

        this.encryptionScheme = encryptionScheme;
        count++;
    }

    /**
     * Get's the total number of PostEncryptedFormRequest's made
     *
     * @return total number of PostEncryptedFormRequest's made
     */
    public static int count()
    {
        return count;
    }

    /**
     * Get's the encryption scheme name
     *
     * @return encryption scheme's name
     */
    @Override
    public String getEncryptionScheme()
    {
        return encryptionScheme;
    }

    /**
     * Set's new encryption scheme's name
     *
     * @param encryptionScheme new encryption scheme's name
     */
    @Override
    public void setEncryptionScheme(String encryptionScheme)
    {
        this.encryptionScheme = encryptionScheme;
    }

    /**
     * Creates a more human-readable string
     *
     * @return human-readable string
     */
    @Override
    public String toString()
    {
        return super.toString() + "\n"
                + "This form was encrypted using: " + encryptionScheme;
    }

}
