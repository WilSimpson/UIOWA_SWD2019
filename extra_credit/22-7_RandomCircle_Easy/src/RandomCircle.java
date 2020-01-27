import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * The main class that runs the program. This class constructs a new JFrame and paints a randomly sized circle with
 * detailed information about the parameters of the circle below it. Every time the program is run a randomly sized
 * circle between the sizes defined in the MyCircle class and the corresponding information about the circle is painted
 * to the frame. The only way to create a new circle is by exiting and re-running the program.
 *
 * @TODO: Go over JavaDocs and inline comments to make sure they are sufficient and correct
 *
 *
 * @FUNTODO: Add a option to create a new circle by "File -> Generate New Circle"
 *
 * @author Wil Simpson
 */
public class RandomCircle {
    /**
     * The entry point of the program. No arguments/parameters are checked or required for this program.
     *
     * @param args unused arguments given by the runtime arguments
     */
    public static void main(String[] args) {
        //The frame the application will be using
        JFrame frame = new JFrame("Random Circle");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create our circle then create the circle and textarea panels
        MyCircle circle = new MyCircle();
        CirclePanel drawCirclePanel = new CirclePanel(circle);
        JTextArea textArea = new JTextArea(1, 1);

        //Setup the textarea and make it look good
        textArea.setLineWrap(false);
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        textArea.setMargin(new Insets(10, 10, 0, 10));

        //Add all of the required information to the textarea
        textArea.append(String.format("AREA: %.2f %n", circle.getArea()));
        textArea.append("RADIUS: "+circle.getRadius()+"\n");
        textArea.append("DIAMETER: "+circle.getDiameter()+"\n");
        textArea.append(String.format("CIRCUMFERENCE: %.2f %n", circle.getCircumference()));

        //Add all components to the frame
        frame.add(drawCirclePanel, BorderLayout.NORTH);
        frame.add(textArea, BorderLayout.SOUTH);

        //Pack the frame to fit everything perfectly and make it visible
        frame.pack();
        frame.setVisible(true);
    }
}
