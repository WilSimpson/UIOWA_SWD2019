import java.util.UUID;

public class PostEncryptedPaymentRequest extends PostPaymentRequest
{
    private static int count = 0;

    private String encryptionScheme;

    public PostEncryptedPaymentRequest(UUID uuid, String ip, Payment payment, String encryptionScheme)
    {
        super(uuid, ip, payment);

        this.encryptionScheme = encryptionScheme;
        count++;
    }

    public static int count()
    {
        return count;
    }

    @Override
    public String toString()
    {
        return super.toString()+"\n"
                +"This form was encrypted using: "+encryptionScheme;
    }
}
