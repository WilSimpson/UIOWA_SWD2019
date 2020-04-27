import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

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
                currentOrder.setSection(this);
                //Thread.sleep(random.nextInt(5000));
                //Thread.sleep(random.nextInt(1));
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(0, 1));
                ob_SD.putBlocking(currentOrder);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        ob_SD.setUpstreamFinished();
        System.out.println("Section: Finished processing all orders");
    }

    public int getNumber()
    {
        return number;
    }
}
