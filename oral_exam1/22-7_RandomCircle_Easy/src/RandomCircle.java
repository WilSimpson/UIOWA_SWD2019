import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * This class constructs a new JFrame and paints a randomly sized circle with
 * detailed information about the parameters of the circle below it. Every time the program is run a randomly sized
 * circle between the sizes defined in the MyCircle class and the corresponding information about the circle is painted
 * to the frame. The only way to create a new circle is by exiting and re-running the program.
 *
 * @author Wil Simpson
 */
public class RandomCircle
{
    /**
     * Circle that we will use
     */
    private MyCircle circle;

    /**
     * Panel that will draw our circle
     */
    private CirclePanel panel;

    /**
     * Text area to print information about the circle
     */
    private JTextArea textArea;

    /**
     * Initializes all the elements for the GUI. Creates a new random circle. Calculates the values and puts them in the
     * textarea. Puts everything in the frame and and makes it visible.
     */
    public void run()
    {
        //The frame the application will be using
        JFrame frame = new JFrame("Random Circle");
        frame.getContentPane().setBackground(Color.WHITE);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create our circle then create the circle and textarea panels
        circle = new MyCircle();
        panel = new CirclePanel(circle);
        textArea = new JTextArea(1, 1);

        //Setup the textarea and make it look good
        textArea.setLineWrap(false);
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        textArea.setMargin(new Insets(10, 10, 0, 10));

        setupTextArea();

        //Add all components to the frame
        frame.add(panel, BorderLayout.NORTH);
        frame.add(textArea, BorderLayout.SOUTH);

        //Pack the frame to fit everything perfectly and make it visible
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Setups the textarea to contain all of the information about the circle
     */
    private void setupTextArea()
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
