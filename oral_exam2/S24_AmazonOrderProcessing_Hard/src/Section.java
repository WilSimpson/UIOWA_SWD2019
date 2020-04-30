import java.security.SecureRandom;

public class Section extends Node
{
    SecureRandom random = new SecureRandom();

    private int number;

    public Section(Buffer ib_SC, Buffer ob_SD, int number)
    {
        super(ib_SC, new Buffer[]{ob_SD});
        this.number = number;
    }

    @Override
    public void doOperations()
    {
        while(!getInputBuffer().isEmpty())
        {
            try
            {
                Order currentOrder = getBlocking();
                currentOrder.setSection(this);
                Thread.sleep(random.nextInt(5000));
                //Thread.sleep(random.nextInt(1));
                putBlocking(currentOrder);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doFinally()
    {
        System.out.println("Section: Finished processing all orders");
        debugNode();
    }

    public int getNumber()
    {
        return number;
    }
}
