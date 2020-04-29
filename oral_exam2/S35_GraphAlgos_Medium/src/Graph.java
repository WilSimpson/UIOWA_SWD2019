import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Graph<T>
{
    private ArrayList<T> verts = new ArrayList<>();

    public Graph(String inputFile)
    {
        try
        {
            BufferedReader br = new BufferedReader(new java.io.FileReader(inputFile));
            String currentLine = "";
            while((currentLine = br.readLine()) != null)
            {
                verts.add((T) currentLine);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<T> getVerts()
    {
        return verts;
    }
}
