import java.util.UUID;

public class Request
{
    private static int count = 0;

    private final UUID uuid;

    public Request(UUID uuid)
    {
        this.uuid = uuid;

        count++;
    }

    public static int count()
    {
        return count;
    }

    public UUID getUUID()
    {
        return uuid;
    }

    @Override
    public String toString()
    {
        return "RequestInheritance."+super.toString()+"\n"
              +"UUID: "+uuid;
    }
}
