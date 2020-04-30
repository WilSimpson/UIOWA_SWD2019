/**
 * Runs an example server
 */
public class ServerTest
{
    /**
     * Starts the server. No runtime arguments are checked for.
     *
     * @param args runtime arguments
     */
    public static void main(String[] args)
    {
        Server server = new Server();
        server.waitForPackets();
    }
}
