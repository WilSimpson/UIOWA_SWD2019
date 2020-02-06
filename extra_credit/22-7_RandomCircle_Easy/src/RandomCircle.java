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
    private MyCircle circle;
    private CirclePanel panel;
    private JTextArea textArea;

    public void run()
    {
        //The frame the application will be using
        JFrame frame = new JFrame("Random Circle");

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

    private synchronized final void setupTextArea()
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
