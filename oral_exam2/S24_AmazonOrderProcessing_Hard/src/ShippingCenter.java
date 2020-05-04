/**
 * Represents a shipping center. Gets orders from the input buffer and assigns them to the either output buffer 1 or 2
 * depending on the starting letter of the category. Categories starting with A-P will go to output buffer 1 while all
 * others will go to output buffer 2.
 *
 * @author Wil Simpson
 */
public class ShippingCenter extends Node<Order>
{
    /**
     * Shipping center number
     */
    private int number;

    /**
     * Creates a new shipping center
     *
     * @param ib_AWS input buffer from the amazon web server
     * @param ob_S1 output buffer to section 1
     * @param ob_S2 output buffer to section 2
     * @param number section number
     */
    public ShippingCenter(Buffer<Order> ib_AWS, Buffer<Order> ob_S1, Buffer<Order> ob_S2, int number)
    {
        super(ib_AWS, new Buffer[]{ob_S1, ob_S2});
        this.number = number;
    }

    /**
     * Gets the number for the shipping center
     *
     * @return shipping center number
     */
    public int getNumber()
    {
        return number;
    }

    /**
     * Gets orders from the input buffer and assigns them to the either output buffer 1 or 2 depending on the starting
     * letter of the category. Categories starting with A-P will go to output buffer 1 while all others will go to
     * output buffer 2.
     */
    @Override
    public void doOperations()
    {
        Order currentOrder = getBlocking();
        if(currentOrder != null)
        {
            currentOrder.setShippingCenter(this);

            if(currentOrder.getCategory().toLowerCase().charAt(0) <= 'p')
            {
                putBlocking(currentOrder, 0);
            }
            else
            {
                putBlocking(currentOrder, 1);
            }
        }
    }

    /**
     * Prints to the console that the shipping center is done processing orders
     */
    @Override
    public void doFinally()
    {
        //System.out.println("SC: Finished processing all orders");
    }
}
