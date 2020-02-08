public class Video
{
    private String url;
    private String videoTitle;
    private String author;

    public Video(String url, String videoTitle, String author)
    {
        this.url = url;
        this.videoTitle = videoTitle;
        this.author = author;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getVideoTitle()
    {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle)
    {
        this.videoTitle = videoTitle;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    @Override
    public String toString()
    {
        return "Video: "+videoTitle+"\n"
              +"By: "+ author;
    }
}
