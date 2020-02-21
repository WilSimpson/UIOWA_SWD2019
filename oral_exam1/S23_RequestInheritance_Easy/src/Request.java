import java.util.UUID;

/**
 * A represents a request to a server and requires a uuid to be created
 *
 * @author Wil Simpson
 */
public class Request
{
    /**
     * Total number of Request's made
     */
    private static int count = 0;

    /**
     * UUID for the request
     */
    private final UUID uuid;

    /**
     * Creates a new request given a uuid
     *
     * @param uuid uuid of the request
     */
    public Request(UUID uuid)
    {
        this.uuid = uuid;

        count++;
    }

    /**
     * Gets the total number of Request's made
     *
     * @return the total number of Request's made
     */
    public static int count()
    {
        return count;
    }

    /**
     * Gets the UUID of the request
     *
     * @return uuid of the request
     */
    public UUID getUUID()
    {
        return uuid;
    }

    /**
     * Creates a more human-readable string
     *
     * @return human-readable string
     */
    @Override
    public String toString()
    {
        return "RequestInheritance." + super.toString() + "\n"
                + "UUID: " + uuid;
    }
}
