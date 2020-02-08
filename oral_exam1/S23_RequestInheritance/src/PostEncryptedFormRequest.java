import java.util.UUID;

public class PostEncryptedFormRequest extends PostFormRequest
{
    private static int count = 0;

    private String encryptionScheme;

    public PostEncryptedFormRequest(UUID uuid, String ip, Form form, String encryptionScheme)
    {
        super(uuid, ip, form);

        this.encryptionScheme = encryptionScheme;
        count++;
    }

    public static int count()
    {
        return count;
    }


    public String getEncryptionScheme()
    {
        return encryptionScheme;
    }

    public void setEncryptionScheme(String encryptionScheme)
    {
        this.encryptionScheme = encryptionScheme;
    }

    @Override
    public String toString()
    {
        return super.toString()+"\n"
                +"This form was encrypted using: "+encryptionScheme;
    }

}
