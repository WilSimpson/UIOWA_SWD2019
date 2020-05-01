import java.security.SecureRandom;

public class Section implements Runnable
{
    SecureRandom random = new SecureRandom();

    private OrderBuffer ib_SC;
    private OrderBuffer ob_SD;

    private int number;

    public Section(OrderBuffer ib_SC, OrderBuffer ob_SD, int number)
    {
        this.ib_SC = ib_SC;
        this.ob_SD = ob_SD;
        this.number = number;
    }

    @Override
    public synchronized void run()
    {
        while(ib_SC.shouldContinueAcceptingInput())
        {
            try
            {
                Order currentOrder = ib_SC.getBlocking();
                DebugUtil.debug(String.format("%s%d: Processing order %s", getClass(), number, currentOrder), DebugUtil.MessagePriority.LOW);
                currentOrder.setSection(this);
                //Thread.sleep(random.nextInt(5000));
                Thread.sleep(random.nextInt(1));
                ob_SD.putBlocking(currentOrder);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        ob_SD.setUpstreamFinished();
        notifyAll();
        DebugUtil.debug("Section: Finished processing all orders", DebugUtil.MessagePriority.NORMAL);
    }

    public int getNumber()
    {
        return number;
    }
}
