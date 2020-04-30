import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX application that lets you play the game hangman!
 */
public class Hangman extends Application
{

    /**
     * Launches the program
     *
     * @param args runtime arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * Creates the GUI and shows it
     *
     * @param primaryStage main application stage
     * @throws IOException thrown if error while creating the GUI
     */
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("HangmanFX.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setTitle("Hangman");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
