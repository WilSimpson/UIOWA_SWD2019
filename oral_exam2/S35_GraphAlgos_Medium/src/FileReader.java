import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class FileReader
{
    public static ArrayList<String> fileToList(String fileName)
    {
        ArrayList<String> list = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new java.io.FileReader(fileName));
            String currentLine = "";
            while((currentLine = br.readLine()) != null)
            {
                list.add(currentLine);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return list;
    }
}
