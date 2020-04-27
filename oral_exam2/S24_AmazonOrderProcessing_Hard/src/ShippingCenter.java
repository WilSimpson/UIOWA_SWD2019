public class ShippingCenter implements Runnable
{
    private OrderBuffer ib_AWS;
    private OrderBuffer ob_S1;
    private OrderBuffer ob_S2;

    private int number;

    public ShippingCenter(OrderBuffer ib_AWS, OrderBuffer ob_S1, OrderBuffer ob_S2, int number)
    {
        this.ib_AWS = ib_AWS;
        this.ob_S1 = ob_S1;
        this.ob_S2 = ob_S2;
        this.number = number;
    }

    public int getNumber()
    {
        return number;
    }

    @Override
    public synchronized void run()
    {
        while(ib_AWS.shouldContinueAcceptingInput())
        {
            Order currentOrder = ib_AWS.getBlocking();
            currentOrder.setShippingCenter(this);

            if(currentOrder.getCategory().toLowerCase().charAt(0) <= 'p')
            {
                ob_S1.putBlocking(currentOrder);
            }
            else
            {
                ob_S2.putBlocking(currentOrder);
            }
        }

        ob_S1.setUpstreamFinished();
        ob_S2.setUpstreamFinished();
        System.out.println("SC: Finished processing all orders");
    }
}
