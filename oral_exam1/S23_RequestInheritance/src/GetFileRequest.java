import java.util.UUID;

public class GetFileRequest extends GetRequest
{
    private static int count = 0;

    private File file;

    public GetFileRequest(UUID uuid, File file)
    {
        super(uuid, "file://"+file.getFilePath());

        this.file = file;
        count++;
    }

    public static int count()
    {
        return count;
    }

    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
    {
        this.file = file;
        setUrl(file.toString());
    }

    @Override
    public String toString()
    {
        return super.toString()+"\n"
              +file;
    }
}
