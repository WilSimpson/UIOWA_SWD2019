import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX Application that lets you open and create files. When run in conjunction with the a server running on port
 * 5000, the save function sends the file to the server for storage in memory.
 *
 * @author Wil Simpson
 */
public class ModifyFileClient extends Application
{

    /**
     * Call to start the application. No runtime arguments are required.
     *
     * @param args runtime arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * Starts the JavaFX application. Calling handled by JavaFX
     *
     * @param primaryStage parent/primary stage
     * @throws IOException thrown if issues starting the application
     */
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("ModifyFileFX.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setTitle("File Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
