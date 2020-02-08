import java.util.UUID;

public class PostRequest extends Request
{
    private static int count = 0;

    private String ip;

    public PostRequest(UUID uuid, String ip)
    {
        super(uuid);

        this.ip = ip;
        count++;
    }

    public static int count()
    {
        return count;
    }

    public String getIP()
    {
        return ip;
    }

    public void setIP(String ip)
    {
        this.ip = ip;
    }

    @Override
    public String toString()
    {
        return super.toString()+"\n"+
                "Post request to server with IP address: 85.41.15.246";
    }
}
