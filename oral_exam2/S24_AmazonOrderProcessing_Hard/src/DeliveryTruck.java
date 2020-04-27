import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class DeliveryTruck implements Runnable
{
    private SecureRandom random = new SecureRandom();

    private OrderBuffer ib_SD;
    private int truckNumber;

    private int deliveriesCompleted = 0;

    public DeliveryTruck(OrderBuffer ib_SD, int truckNumber)
    {
        this.ib_SD = ib_SD;
        this.truckNumber = truckNumber;
    }

    @Override
    public synchronized void run()
    {
        while(ib_SD.shouldContinueAcceptingInput())
        {
            try
            {
                Order currentOrder = ib_SD.getBlocking();
                currentOrder.setDeliveryTruck(this);
                //Thread.sleep(random.nextInt(10*1000));
                //Thread.sleep(random.nextInt(1));
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(0, 1));
                currentOrder.setDelivered();
                System.out.println(currentOrder);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        System.out.println("Truck "+truckNumber+": No more deliveries!");
    }

    public int getTruckNumber()
    {
        return truckNumber;
    }
}
