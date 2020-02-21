import javax.swing.*;
import java.awt.*;

/**
 * This panel extends the JPanel and deals with painting the circle to the screen. A MyCircle object is required to
 * construct this object. Once supplied the class overrides the paint and preferredSize to easily paint the given
 * circle to a JFrame
 *
 * @author Wil Simpson
 */
public class CirclePanel extends JPanel {

    /**
     * Minimum distance circle will be from all directions to the edge of the panel
     */
    public static final int CIRCLE_EDGE_OFFSET = 10;

    /**
     * The width the of the panel
     */
    public static final int PANEL_WIDTH = 500;

    /**
     * The height of the panel
     */
    public static final int PANEL_HEIGHT = 500;

    /**
     * The unchanging MyCircle object that we will be using and drawing
     */
    private MyCircle circle;

    /**
     * The constructor creates an object with a given MyCircle and setups the working space for the panel
     *
     * @param circle the MyCircle object that is to be drawn
     */
    public CirclePanel(MyCircle circle) {
        this.circle = circle;
        setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        setBackground(Color.WHITE);
    }

    /**
     * The constructor creates a MyCircle and setups the working space for the panel
     */
    public CirclePanel()
    {
        this(new MyCircle());
    }

    /**
     * Generates a new circle and repaints the panel
     */
    public void generateNewCircle()
    {
        circle = new MyCircle();
        repaint();
    }

    /**
     * Gets the circle that is being drawn
     *
     * @return circle that is being drawn
     */
    public MyCircle getCircle()
    {
        return circle;
    }

    /**
     * Overriding the preferred size to that of the exact size of the MyCircle object
     *
     * @return Dimension that exactly contains the circle
     */
    @Override
    public Dimension getPreferredSize() {
        //The dimension of the circle is the preferred size plus the minimum distance defined by CIRCLE_EDGE_OFFSET
        //Value must be multiplied by two to calculate for all 4 sides of the circle
        return new Dimension(circle.getRadius()+CIRCLE_EDGE_OFFSET*2, circle.getRadius()+CIRCLE_EDGE_OFFSET*2);
    }

    /**
     * Overriding the paint method to draw our circle. The circle is draw from x:0,y:0 at the top left of the panel.
     *
     * @param g the graphics object required to draw our circle
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawOval(CIRCLE_EDGE_OFFSET, CIRCLE_EDGE_OFFSET, circle.getRadius(), circle.getRadius());
    }
}
