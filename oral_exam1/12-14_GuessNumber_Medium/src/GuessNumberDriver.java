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

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem showNumberMenuItem = new JMenuItem("Show number");
        menu.add(showNumberMenuItem);
        showNumberMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, game.getNumber()));
        frame.setJMenuBar(menuBar);

        //Add the panel, set the size and show the GUI
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
    }
}
