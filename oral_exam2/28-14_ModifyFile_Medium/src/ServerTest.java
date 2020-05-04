import javax.swing.*;

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
        server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        server.waitForPackets();
    }
}
