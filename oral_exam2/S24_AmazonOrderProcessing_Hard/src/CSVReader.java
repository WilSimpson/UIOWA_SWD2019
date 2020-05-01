import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads values from a given CSV file
 *
 * @author Wil Simpson
 */
public class CSVReader
{
    /**
     * Buffered read to handle stream from file
     */
    private BufferedReader br;

    /**
     * Whether the end of the file has been reached
     */
    private boolean endOfFileReached = false;

    /**
     * Creates a new CSVReader from the given input file.
     *
     * @param inputFile path to csv file
     * @param skipFirstLine whether the first line should be skipped
     */
    public CSVReader(String inputFile, boolean skipFirstLine)
    {
        try
        {
            br = new BufferedReader(new FileReader(inputFile));

            if(skipFirstLine) br.readLine();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Gets the next line from the CSV file
     *
     * @return next string values from csv file
     */
    public String[] getNextLine()
    {
        if(finishedReading()) return null;

        try
        {
            String currentLine = br.readLine();

            if(currentLine == null)
            {
                endOfFileReached = true;
                br.close();
                return null;
            }

            return currentLine.split(",");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Checks if the CSV reader has finished reading the file
     *
     * @return true if the file is done being read
     */
    public boolean finishedReading()
    {
        return endOfFileReached;
    }
}
