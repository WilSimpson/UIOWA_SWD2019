import java.util.UUID;

public class GetRequest extends Request
{
    private static int count = 0;

    private String url;

    public GetRequest(UUID randUUID, String url)
    {
        super(randUUID);

        this.url = url;
        count++;
    }

    public static int count()
    {
        return count;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return super.toString()+"\n"
                +"Universal Resource Locator (URL): "+url;
    }
}
