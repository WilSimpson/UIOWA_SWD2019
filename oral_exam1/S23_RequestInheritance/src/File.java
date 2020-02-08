public class File
{
    private String filePath;
    private String fileType;

    public File(String filePath, String fileType)
    {
        this.filePath = filePath;
        this.fileType = fileType;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public String getFileType()
    {
        return fileType;
    }

    public void setFilePath(String filePath)
    {
        this.fileType = filePath;
    }

    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    @Override
    public String toString()
    {
        return "File Path: "+filePath+"\n"
              +"File Type: "+fileType;
    }
}
