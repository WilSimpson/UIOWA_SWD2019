import java.util.HashMap;

/**
 * Represents a form with labels and a value for each.
 *
 * @author Wil Simpson
 */
public class Form
{
    /**
     * Map of all the form data
     */
    private HashMap<String, String> data;

    /**
     * Creates a new form given a new map
     *
     * @param data new field map
     */
    public Form(HashMap<String, String> data)
    {
        this.data = data;
    }

    /**
     * Gets the form data
     *
     * @return form data
     */
    public HashMap<String, String> getData()
    {
        return data;
    }

    /**
     * Sets the form data
     *
     * @param data new data
     */
    public void setData(HashMap<String, String> data)
    {
        this.data = data;
    }

    /**
     * Adds more data to the form
     *
     * @param key key for the data
     * @param value value of the data
     */
    public void addData(String key, String value)
    {
        if(key != null) data.put(key, value);
    }

    /**
     * Removes the given data from the form
     *
     * @param key data to remove
     */
    public void removeData(String key)
    {
        if(key != null) data.remove(key);
    }

    /**
     * Creates a more human-readable string
     *
     * @return human-readable string
     */
    @Override
    public String toString()
    {
        String string = "Form Data";

        if(data.size() == 0)
        {
            return string;
        }

        string += "\n";

        for(String s : data.keySet())
        {
            string += "Label: " + s + "\n"
                    + "Value: " + data.get(s) + "\n";
        }

        return string.substring(0, string.length() - 1);
    }
}
