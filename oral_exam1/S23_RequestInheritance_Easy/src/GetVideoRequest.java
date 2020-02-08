import java.util.UUID;

/**
 * Represents a get video requests and requires a uuid and a video to be created
 *
 * @author Wil Simpson
 */
public class GetVideoRequest extends GetRequest
{
    /**
     * Total number of GetVideoRequest's made
     */
    private static int count = 0;

    /**
     * Video request
     */
    private Video video;

    /**
     * Create a new get video request given a uuid and video
     *
     * @param uuid uuid of request
     * @param video video for request
     */
    public GetVideoRequest(UUID uuid, Video video)
    {
        super(uuid, video.getUrl());

        this.video = video;
        count++;
    }

    /**
     * Get the total number of GetVideoRequest's made
     * @return
     */
    public static int count()
    {
        return count;
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
                + video;
    }
}
