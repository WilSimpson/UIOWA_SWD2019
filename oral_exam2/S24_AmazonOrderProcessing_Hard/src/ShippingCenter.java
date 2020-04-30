public class ShippingCenter extends Node
{
    private int number;

    public ShippingCenter(Buffer ib_AWS, Buffer ob_S1, Buffer ob_S2, int number)
    {
        super(ib_AWS, new Buffer[]{ob_S1, ob_S2});
        this.number = number;
    }

    public int getNumber()
    {
        return number;
    }

    @Override
    public void doOperations()
    {
        while(!getInputBuffer().isEmpty())
        {
            Order currentOrder = getBlocking();
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

    @Override
    public void doFinally()
    {
        System.out.println("SC: Finished processing all orders");
        debugNode();
    }
}
