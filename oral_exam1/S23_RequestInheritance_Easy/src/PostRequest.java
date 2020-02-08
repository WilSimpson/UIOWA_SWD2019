import java.util.UUID;

/**
 * Represents a post request and requires a uuid and an ip to be created
 *
 * @author Wil Simpson
 */
public class PostRequest extends Request
{
    /**
     * Total number of PostRequest's made
     */
    private static int count = 0;

    /**
     * IP for the request
     */
    private String ip;

    /**
     * Creates a new post request given a uuid and ip
     *
     * @param uuid uuid of request
     * @param ip ip for the request
     */
    public PostRequest(UUID uuid, String ip)
    {
        super(uuid);

        this.ip = ip;
        count++;
    }

    /**
     * Gets the total number of PostRequest's made
     *
     * @return the total number of PostRequest's made
     */
    public static int count()
    {
        return count;
    }

    /**
     * Gets the IP for the request
     *
     * @return ip for the request
     */
    public String getIP()
    {
        return ip;
    }

    /**
     * Sets the ip for the request
     * @param ip new ip for the request
     */
    public void setIP(String ip)
    {
        this.ip = ip;
    }

    /**
     * Creates a more human-readable string
     *
     * @return human-readable string
     */
    @Override
    public String toString()
    {
        return super.toString() + "\n" +
                "Post request to server with IP address: 85.41.15.246";
    }
}
