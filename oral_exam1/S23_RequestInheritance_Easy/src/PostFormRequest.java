import java.util.UUID;

/**
 * Represents a post form requests and requires a uuid, ip and form to be created
 *
 * @author Wil Simpson
 */
public class PostFormRequest extends PostRequest
{
    /**
     * Total number of PostFormRequest's made
     */
    private static int count = 0;

    /**
     * Form for the request
     */
    private Form form;

    /**
     * Creates a new post form request given a uuid, ip and form
     *
     * @param uuid uuid of request
     * @param ip ip for request
     * @param form form for request
     */
    public PostFormRequest(UUID uuid, String ip, Form form)
    {
        super(uuid, ip);

        this.form = form;
        count++;
    }

    /**
     * Get's the total number of PostFormRequest's made
     * @return total number of PostFormRequest's made
     */
    public static int count()
    {
        return count;
    }

    /**
     * Gets the form for the request
     *
     * @return form for the request
     */
    public Form getForm()
    {
        return form;
    }

    /**
     * Sets the form for the request
     *
     * @param form new request form
     */
    public void setForm(Form form)
    {
        this.form = form;
    }

    /**
     * Creates a more human-readable string
     *
     * @return human-readable string
     */
    @Override
    public String toString()
    {
        return super.toString() + "\n"
                + form;
    }
}
