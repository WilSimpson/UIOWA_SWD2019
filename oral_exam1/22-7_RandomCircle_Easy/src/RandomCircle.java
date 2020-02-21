import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

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
     * Circle that we will use
     */
    private MyCircle circle;

    /**
     * Panel that will draw our circle
     */
    private final CirclePanel panel;

    /**
     * Text area to print information about the circle
     */
    private final JTextArea textArea;

    /**
     * Creates the default values for the member variables
     */
    public RandomCircle()
    {
        super("Random Circle");
        circle = new MyCircle();
        panel = new CirclePanel(circle);
        textArea = new JTextArea(4, 1);

        //Setup frame
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Edit");
        JMenuItem newCircleMenuItem = new JMenuItem("New Circle");

        newCircleMenuItem.setMnemonic(KeyEvent.VK_N);
        newCircleMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));

        fileMenu.add(newCircleMenuItem);
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
     * Creates a new circle and shows it's properties to the user
     */
    public void run()
    {
        circle = new MyCircle();

        //Calculate the circle properties and setup the text area with their values
        generateTextArea();

        //Pack the frame to fit everything perfectly and make it visible
        pack();
        setVisible(true);
    }

    /**
     * Setups the textarea to contain all of the information about the circle
     */
    private void generateTextArea()
    {
        //Clear the textArea
        textArea.selectAll();
        textArea.replaceSelection("");

        //Add all of the required information to the textarea
        textArea.append(String.format("AREA: %.2f %n", circle.getArea()));
        textArea.append("RADIUS: "+circle.getRadius()+"\n");
        textArea.append("DIAMETER: "+circle.getDiameter()+"\n");
        textArea.append(String.format("CIRCUMFERENCE: %.2f %n", circle.getCircumference()));
    }
}
