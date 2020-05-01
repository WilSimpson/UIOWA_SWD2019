/**
 * Represents a shipping docks. Takes the input buffer and attempts to put the order into the output buffers. It checks
 * the first buffer then the second one and keeps checking until it can place it in the queue.
 *
 * @author Wil Simpson
 */
public class ShippingDock extends Node<Order>
{
    /**
     * Creates a new shipping center
     *
     * @param ib_S input buffer
     * @param ob_DT1 output buffer to truck1
     * @param ob_DT2 output buffer to truck2
     */
    public ShippingDock(Buffer<Order> ib_S, Buffer<Order> ob_DT1, Buffer<Order> ob_DT2)
    {
        super(ib_S, new Buffer[]{ob_DT1, ob_DT2});
    }

    /**
     * Gets an order from the input buffer and attempts to put the order into the output buffers. It checks
     * the first buffer then the second one and keeps checking until it can place it in the queue.
     */
    @Override
    public void doOperations()
    {
        while(!getInputBuffer().isEmpty())
        {
            Buffer<Order>[] ob = getOutputBuffers();
            Order order = getBlocking();
            int i = 0;
            while(i++ >= 0)
            {
                int currentIndex = i%ob.length;
                if(!ob[currentIndex].isFull())
                {
                    ob[currentIndex].putBlocking(order);
                    return;
                }
                i++;
            }
        }
    }

    /**
     * Prints that it's finished processing all orders
     */
    @Override
    public void doFinally()
    {
        System.out.println("SD: Finished distributing all orders");
    }
}
