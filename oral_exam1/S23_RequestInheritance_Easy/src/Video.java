/**
 * Represents a video file and requires a url, title and author to be created
 *
 * @author Wil Simpson
 */
public class Video
{
    /**
     * URL for the video
     */
    private String url;

    /**
     * Title of the video
     */
    private String title;

    /**
     * Author/Creator of the video
     */
    private String author;

    /**
     * Creates a new video given a url, title and author
     *
     * @param url url for the video
     * @param title title of the video
     * @param author author/creator of the video
     */
    public Video(String url, String title, String author)
    {
        this.url = url;
        this.title = title;
        this.author = author;
    }

    /**
     * Gets the URL for the video
     *
     * @return url for the video
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * Sets the URL for the video
     *
     * @param url new URL for the video
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * Gets the title of the video
     *
     * @return video title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets the title of the video
     *
     * @param title new title of the video
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Gets the author/creator of the video
     *
     * @return author/creator of the video
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * Sets the author/creator of the video
     *
     * @param author new author/creator of the video
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }

    /**
     * Creates a more human-readable string
     *
     * @return human-readable string
     */
    @Override
    public String toString()
    {
        return "Video: " + title + "\n"
                + "By: " + author;
    }
}
