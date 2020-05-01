import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader
{
    private String csvSplitChar;
    private BufferedReader br;
    private String currentLine;
    private boolean endOfFileReached = false;

    public CSVReader(String inputFileName, String csvSplitChar, boolean skipFirstLine)
    {
        this.csvSplitChar = csvSplitChar;

        try
        {
            br = new BufferedReader(new FileReader(inputFileName));

            if(skipFirstLine) br.readLine();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


    }

    public String[] getNextLine()
    {
        if(finishedReading()) return null;

        try
        {
            currentLine = br.readLine();

            if(currentLine == null)
            {
                endOfFileReached = true;
                br.close();
                return null;
            }

            return currentLine.split(csvSplitChar);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public boolean finishedReading()
    {
        return endOfFileReached;
    }
}
