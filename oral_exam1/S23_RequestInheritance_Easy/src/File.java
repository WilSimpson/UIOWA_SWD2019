/**
 * Represents a file having a path and a type
 *
 * @author Wil Simpson
 */
public class File
{
    /**
     * Path to file
     */
    private String filePath;

    /**
     * Type of file
     */
    private String fileType;

    /**
     * Creates a file given a path and type
     *
     * @param filePath path to file
     * @param fileType type of file
     */
    public File(String filePath, String fileType)
    {
        this.filePath = filePath;
        this.fileType = fileType;
    }

    /**
     * Returns the file path to the file
     *
     * @return file path
     */
    public String getFilePath()
    {
        return filePath;
    }

    /**
     * Sets the file path to the given path
     *
     * @param filePath new file path
     */
    public void setFilePath(String filePath)
    {
        this.fileType = filePath;
    }

    /**
     * Gets the file type
     *
     * @return file type
     */
    public String getFileType()
    {
        return fileType;
    }

    /**
     * Sets the file type
     *
     * @param fileType new file type
     */
    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    /**
     * Creates a more human-readable string
     *
     * @return human-readable string
     */
    @Override
    public String toString()
    {
        return "File Path: " + filePath + "\n"
                + "File Type: " + fileType;
    }
}
