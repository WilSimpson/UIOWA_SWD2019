/**
 * Something is encrypted if it contains an encryption scheme
 *
 * @author Wil Simpson
 */
public interface Encrypted
{
    String getEncryptionScheme();

    void setEncryptionScheme(String encryptionScheme);
}
