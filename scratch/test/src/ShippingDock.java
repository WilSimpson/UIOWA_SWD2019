public class ShippingDock implements Runnable
{
    private OrderBuffer ib_S;
    private OrderBuffer ob_DT1;
    private OrderBuffer ob_DT2;

    public ShippingDock(OrderBuffer ib_S, OrderBuffer ob_DT1, OrderBuffer ob_DT2)
    {
        this.ib_S = ib_S;
        this.ob_DT1 = ob_DT1;
        this.ob_DT2 = ob_DT2;
    }

    @Override
    public synchronized void run()
    {
        while(ib_S.shouldContinueAcceptingInput())
        {
            Order currentOrder = ib_S.getBlocking();
            DebugUtil.debug(String.format("%s: Processing order %s", getClass(), currentOrder), DebugUtil.MessagePriority.LOW);

            if(!ob_DT1.isBufferFull())
            {
                ob_DT1.putBlocking(currentOrder);
            }
            else if(!ob_DT2.isBufferFull())
            {
                ob_DT2.putBlocking(currentOrder);
            }
            else
            {
                ob_DT1.putBlocking(currentOrder);
            }
        }
        ob_DT1.setUpstreamFinished();
        ob_DT2.setUpstreamFinished();
        notifyAll();
        DebugUtil.debug("Shipping Dock: Finished distributing all orders", DebugUtil.MessagePriority.NORMAL);
    }
}
