import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Controller for the JavaFX client file editor.
 *
 * @author Wil Simspson
 */
public class ModifyFileController
{
    /**
     * Socket connection to the server
     */
    private DatagramSocket socket;

    /**
     * Editable text area the user will type in
     */
    @FXML
    private TextArea textArea;

    /**
     * Called when the user clicks on "File -> Open File..." Creates a FileChooser window where the user will choose
     * a file to open and edit. The textArea will be replaced by the String data of the file.
     *
     * @param event event related to the menu item being clicked
     */
    @FXML
    void openFile(ActionEvent event)
    {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File...");
        File file = chooser.showOpenDialog(textArea.getParent().getScene().getWindow());
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            textArea.setText(br.lines().collect(Collectors.joining("\n")));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Called when the user clicks on "File -> New File". Clears the textArea.
     *
     * @param event event related to the menu item being clicked
     */
    @FXML
    void openNewFile(ActionEvent event)
    {
        textArea.clear();
    }

    /**
     * Called when the user clicks on "File -> Save File". Sends the content of the textArea to the server via a socket
     * on a separate thread invoked through ExecutorService.
     *
     * @param event event related to the menu item being clicked
     */
    @FXML
    void saveFile(ActionEvent event)
    {
        Task<Boolean> task = new Task<Boolean>()
        {
            @Override
            protected Boolean call() throws IOException
            {
                byte[] data = textArea.getText().getBytes();

                DatagramPacket sendPacket = new DatagramPacket(data,
                        data.length, InetAddress.getLocalHost(), 5000);

                socket.send(sendPacket);

                return true;
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(1);
        es.execute(task);
        es.shutdown();
    }

    /**
     * Called when application has been completely processed. Creates the socket connection to the server. If any error
     * occurs the system will exit with status code 1.
     */
    @FXML
    public void initialize()
    {
        try
        {
            socket = new DatagramSocket();
        }
        catch(SocketException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
