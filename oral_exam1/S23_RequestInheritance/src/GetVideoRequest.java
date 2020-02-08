import java.util.UUID;

public class GetVideoRequest extends GetRequest
{
    private static int count = 0;

    private Video video;

    public GetVideoRequest(UUID uuid, Video video)
    {
        super(uuid, video.getUrl());

        this.video = video;
        count++;
    }

    public static int count()
    {
        return count;
    }

    @Override
    public String toString()
    {
        return super.toString()+"\n"
                +video;
    }
}
