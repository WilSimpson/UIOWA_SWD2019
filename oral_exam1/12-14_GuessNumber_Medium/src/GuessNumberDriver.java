import javax.swing.*;

/**
 * Driver for the program. Creates the JFrame, adds the panel that handles the inputs and outputs and shows the GUI.
 *
 * @author Wil Simpson
 */
public class GuessNumberDriver
{
    public static void main(String[] args)
    {
        //Create a new frame
        JFrame frame = new JFrame("Guess Number");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create the panel
        GuessNumber game = new GuessNumber();

        //Add the panel, set the size and show the GUI
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
    }
}
