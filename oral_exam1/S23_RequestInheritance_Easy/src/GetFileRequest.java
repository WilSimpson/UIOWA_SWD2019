import java.util.UUID;

/**
 * Represents a get file request and requires a UUID and File to be created.
 *
 * @author Wil Simpson
 */
public class GetFileRequest extends GetRequest
{
    /**
     * URL prefix for a file
     */
    public static final String FILE_URL_PREFIX = "file://";

    /**
     * Total number of GetFileRequest's made
     */
    private static int count = 0;

    /**
     * File in the request
     */
    private File file;

    /**
     * Creates a new get file request given a uuid and file
     *
     * @param uuid uuid of request
     * @param file file in request
     */
    public GetFileRequest(UUID uuid, File file)
    {
        super(uuid, FILE_URL_PREFIX +file.getFilePath());

        this.file = file;
        count++;
    }

    /**
     * Get the total number of GetFileRequests made
     *
     * @return number of GetFileRequests made
     */
    public static int count()
    {
        return count;
    }

    /**
     * Gets the file in the request
     * @return
     */
    public File getFile()
    {
        return file;
    }

    /**
     * Sets the file in the request
     *
     * @param file new file in request
     */
    public void setFile(File file)
    {
        this.file = file;
        setURL(FILE_URL_PREFIX +file.getFilePath());
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
                + file;
    }
}
