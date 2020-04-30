public class ShippingDock extends Node
{
    public ShippingDock(Buffer ib_S, Buffer ob_DT1, Buffer ob_DT2)
    {
        super(ib_S, new Buffer[]{ob_DT1, ob_DT2});
    }

    @Override
    public void doOperations()
    {
        while(!getInputBuffer().isEmpty())
        {
            placeOrder(getInputBuffer().getBlocking());
        }
    }

    @Override
    public void doFinally()
    {
        System.out.println("SD: Finished distributing all orders");
        debugNode();
    }

    public void placeOrder(Order order)
    {
        Buffer[] ob = getOutputBuffers();
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
