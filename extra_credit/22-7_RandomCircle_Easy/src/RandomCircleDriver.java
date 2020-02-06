/**
 * The main class that starts the program.
 *
 * @TODO: Go over JavaDocs and inline comments to make sure they are sufficient and correct
 *
 *
 * @FUNTODO: Add a option to create a new circle by "File -> Generate New Circle"
 *
 * @author Wil Simpson
 */
public class RandomCircleDriver
{
    /**
     * Starts the program and initiates the GUI. No command line arguments are needed or checked.
     *
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        //Start the program
        RandomCircle program = new RandomCircle();
        program.run();
    }
}
