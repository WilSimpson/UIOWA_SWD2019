import java.util.HashMap;

public class Form
{
    private HashMap<String, String> fields;

    public Form(HashMap<String, String> fields)
    {
        this.fields = fields;
    }

    public HashMap<String, String> getFields()
    {
        return fields;
    }

    public void setFields(HashMap<String, String> fields)
    {
        this.fields = fields;
    }

    @Override
    public String toString()
    {
        String string = "Form Data";

        if(fields.size() == 0)
        {
            return string;
        }

        string += "\n";

        for(String s : fields.keySet())
        {
            string += "Label: "+s+"\n"
                     +"Value: "+fields.get(s)+"\n";
        }

        return string.substring(0, string.length()-1);
    }
}
