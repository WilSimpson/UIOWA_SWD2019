import java.util.UUID;

/**
 * Represents a get request requiring a uuid and url for the request to be created
 *
 * @author Wil Simpson
 */
public class GetRequest extends Request
{
    /**
     * Total number of GetRequest's made
     */
    private static int count = 0;

    /**
     * URL of request
     */
    private String url;

    /**
     * Creates a new GetRequest given a uuid and url
     *
     * @param uuid uuid of request
     * @param url url for request
     */
    public GetRequest(UUID uuid, String url)
    {
        super(uuid);

        this.url = url;
        count++;
    }

    /**
     * Gets the total number of GetRequest's made
     *
     * @return total number of GetRequest's made
     */
    public static int count()
    {
        return count;
    }

    /**
     * Gets the URL for the request
     * @return
     */
    public String getURL()
    {
        return url;
    }

    /**
     * Set the url to the given url
     *
     * @param url new url
     */
    public void setURL(String url)
    {
        this.url = url;
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
                + "Universal Resource Locator (URL): " + url;
    }
}
