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

        //Create a menu bar and add menu items with actions
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem showNumberMenuItem = new JMenuItem("Show Number");
        menu.add(showNumberMenuItem);
        showNumberMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, game.getNumber()));
        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        menu.add(newGameMenuItem);
        newGameMenuItem.addActionListener(e -> game.newGame(false));
        frame.setJMenuBar(menuBar);

        //Add the panel, set the size and show the GUI
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
    }
}
