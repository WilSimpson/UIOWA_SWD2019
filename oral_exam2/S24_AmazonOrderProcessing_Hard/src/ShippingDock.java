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
        Order order = getBlocking();
        if(order != null)
        {
            if(!isOutputBufferFull(0))
            {
                putBlocking(order, 0);
            }
            else if(!isOutputBufferFull(1))
            {
                putBlocking(order, 1);
            }
            else
            {
                putBlocking(order, 0);
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
