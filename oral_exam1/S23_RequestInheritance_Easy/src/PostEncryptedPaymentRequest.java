import java.util.UUID;

/**
 * Represents a post encrypted payment requests and requires a uuid, ip, payment and encryption scheme to be created
 *
 * @author Wil Simpson
 */
public class PostEncryptedPaymentRequest extends PostPaymentRequest implements Encrypted
{
    /**
     * Total number of PostEncryptedPaymentRequest's made
     */
    private static int count = 0;

    /**
     * Encryption scheme used
     */
    private String encryptionScheme;

    /**
     * Creates a new encrypted post payment request given a uuid, ip, payment and encryption scheme
     *
     * @param uuid uuid of request
     * @param ip ip for request
     * @param payment payment for request
     * @param encryptionScheme encryption scheme for request
     */
    public PostEncryptedPaymentRequest(UUID uuid, String ip, Payment payment, String encryptionScheme)
    {
        super(uuid, ip, payment);

        this.encryptionScheme = encryptionScheme;
        count++;
    }

    /**
     * Get's the total number of PostEncryptedPaymentRequest's made
     *
     * @return total number of PostEncryptedPaymentRequest's made
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
    public String getEncryptionScheme()
    {
        return encryptionScheme;
    }

    /**
     * Set's new encryption scheme's name
     *
     * @param encryptionScheme new encryption scheme's name
     */
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
