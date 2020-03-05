/**
 * Something is encrypted if it contains an encryption scheme
 *
 * @author Wil Simpson
 */
public interface Encrypted
{
    /**
     * Get the encryption scheme
     *
     * @return the encryption scheme
     */
    String getEncryptionScheme();

    /**
     * Set the encryption scheme
     *
     * @param encryptionScheme new encryption scheme to use
     */
    void setEncryptionScheme(String encryptionScheme);
}
