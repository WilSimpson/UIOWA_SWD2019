import java.security.SecureRandom;

/**
 * Represents a section of a warehouse. Takes orders from the input buffer and waits a random amount of time between
 * 0 - 5 seconds before sending it to the output buffer.
 *
 * @author Wil Simpson
 */
public class Section extends Node<Order>
{
    /**
     * Random number generator to determine amount of sleep time
     */
    private SecureRandom random = new SecureRandom();

    /**
     * Section number
     */
    private int number;

    /**
     * Creates a new section
     *
     * @param ib_SC input buffer from shipping center
     * @param ob_SD output buffer to shipping dock
     * @param number section number
     */
    public Section(Buffer<Order> ib_SC, Buffer<Order> ob_SD, int number)
    {
        super(ib_SC, new Buffer[]{ob_SD});
        this.number = number;
    }

    /**
     * Gets an item from input buffer, waits a random amount of time between 0 and 5 seconds then puts the order
     * into the output buffer
     */
    @Override
    public void doOperations()
    {
        Order currentOrder = getBlocking();
        if(currentOrder != null)
        {
            try
            {
                currentOrder.setSection(this);
                Thread.sleep(random.nextInt(1));
                //Thread.sleep(random.nextInt(1));
                putBlocking(currentOrder);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Prints to the console that the section is done processing all orders
     */
    @Override
    public void doFinally()
    {
        System.out.println("Section: Finished processing all orders");
    }

    /**
     * Gets the section number
     * @return
     */
    public int getNumber()
    {
        return number;
    }
}
