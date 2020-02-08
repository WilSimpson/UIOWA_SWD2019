import java.util.UUID;

public class PostFormRequest extends PostRequest
{
    private static int count = 0;

    private Form form;

    public PostFormRequest(UUID uuid, String ip, Form form)
    {
        super(uuid, ip);

        this.form = form;
        count++;
    }

    public static int count()
    {
        return count;
    }

    public Form getForm()
    {
        return form;
    }

    public void setForm(Form form)
    {
        this.form = form;
    }

    @Override
    public String toString()
    {
        return super.toString()+"\n"
               +form;
    }
}
