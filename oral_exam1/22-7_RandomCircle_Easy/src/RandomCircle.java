import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

/**
 * This class constructs a new JFrame and paints a randomly sized circle with
 * detailed information about the parameters of the circle below it. Every time the program is run a randomly sized
 * circle between the sizes defined in the MyCircle class and the corresponding information about the circle is painted
 * to the frame. The only way to create a new circle is by exiting and re-running the program.
 *
 * @author Wil Simpson
 */
public class RandomCircle extends JFrame
{
    /**
     * Panel that will draw our circle
     */
    private final CirclePanel panel = new CirclePanel(new MyCircle());

    /**
     * Text area to print information about the circle
     */
    private final JTextArea textArea = new JTextArea(4, 1);

    /**
     * Creates a new panel and sets up the frame
     */
    public RandomCircle()
    {
        super("Random Circle");

        //Setup frame
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Setup the menubar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newCircleMenuItem = new JMenuItem("New Circle");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        newCircleMenuItem.addActionListener(e-> newCircle());
        exitMenuItem.addActionListener(e->dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

        newCircleMenuItem.setMnemonic(KeyEvent.VK_N);
        newCircleMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));

        //Combine menu components and add to the frame
        fileMenu.add(newCircleMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        //Setup the textarea and make it look good
        textArea.setLineWrap(false);
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        textArea.setMargin(new Insets(10, 10, 0, 10));

        //Add all components to the frame
        add(panel, BorderLayout.NORTH);
        add(textArea, BorderLayout.SOUTH);
    }

    /**
     * Generates the text, packs and shows the frame. Frame will not show until this method is called.
     */
    public void run()
    {
        //Generate text, pack and show the frame
        generateTextArea();
        pack();
        setMinimumSize(getSize());
        setVisible(true);
    }

    /**
     * Generates a new circle and shows it's properties to the user
     */
    private void newCircle()
    {
        setMinimumSize(null);

        //Generate a new circle
        panel.generateNewCircle();

        //Calculate the circle properties and setup the text area with their values
        generateTextArea();

        //Repack the frame to fit everything perfectly
        pack();

        setMinimumSize(getSize());
    }

    /**
     * Setups the textarea to contain all of the information about the circle
     */
    private void generateTextArea()
    {
        //Clear the textArea
        textArea.selectAll();
        textArea.replaceSelection("");

        MyCircle circle = panel.getCircle();

        //Add all of the required information to the textarea
        textArea.append(String.format("AREA: %.2f %n", circle.getArea()));
        textArea.append("RADIUS: "+circle.getRadius()+"\n");
        textArea.append("DIAMETER: "+circle.getDiameter()+"\n");
        textArea.append(String.format("CIRCUMFERENCE: %.2f %n", circle.getCircumference()));
    }
}
