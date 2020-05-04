import javafx.concurrent.Task;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server based off Fig. 28.7. Creates a DatagramSocket socket and listens for data on port 5000. Once data is recieved
 * the raw data is stored in local memory in an array.
 *
 * @author Wil Simpson
 * @author Pearson Education
 */
public class Server extends JFrame
{
    /**
     * Displays information related to recieved packets
     */
    private JTextArea displayArea;

    /**
     * Socket to used to receive packets
     */
    private DatagramSocket socket;

    /**
     * Files that have been saved to the server
     */
    private ArrayList<String> files = new ArrayList<>();

    /**
     * Creates all necessary connections, initializes all variables and sets up the GUI display
     */
    public Server()
    {
        super("File Server");

        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        setSize(400, 300);
        setVisible(true);

        try
        {
            socket = new DatagramSocket(5000);
        }
        catch(SocketException socketException)
        {
            socketException.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates a new thread where the server will indefinitely wait for input from the socket. Once any data is received
     * it will be stored in memory in the files ArrayList. A maximum of 10000 bytes of data can be received at a time.
     */
    public void waitForPackets()
    {
        // updates displayArea
        Task<Boolean> task = new Task<Boolean>()
        {
            @Override
            protected Boolean call()
            {
                while(true)
                {
                    try
                    {
                        byte[] data = new byte[10000];
                        DatagramPacket receivePacket =
                                new DatagramPacket(data, data.length);

                        socket.receive(receivePacket);

                        String file = new String(receivePacket.getData(),
                                0, receivePacket.getLength());

                        files.add(file);

                        displayMessage("\nReceived file:" +
                                "\nTotal files stored: " + files.size() +
                                "\nFrom host: " + receivePacket.getAddress() +
                                "\nHost port: " + receivePacket.getPort() +
                                "\nLength: " + receivePacket.getLength() +
                                "\nContaining:\n\t" + file);


                    }
                    catch(IOException ioException)
                    {
                        displayMessage(ioException + "\n");
                        ioException.printStackTrace();
                    }
                }
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(1);
        es.execute(task);
        es.shutdown();
    }


    /**
     * Appends a message to the displayArea TextArea.
     *
     * @param messageToDisplay message to display
     */
    private void displayMessage(final String messageToDisplay)
    {
        SwingUtilities.invokeLater(() -> displayArea.append(messageToDisplay));
    }
}