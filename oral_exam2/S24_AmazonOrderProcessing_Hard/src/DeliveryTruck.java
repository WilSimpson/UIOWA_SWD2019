import java.security.SecureRandom;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class DeliveryTruck implements Runnable
{
    private SecureRandom random = new SecureRandom();

    private OrderBuffer ib_SD;
    private int truckNumber;

    private int deliveriesCompleted = 0;

    ArrayBlockingQueue<Order> deliveries = new ArrayBlockingQueue<>(4);

    public DeliveryTruck(OrderBuffer ib_SD, int truckNumber)
    {
        this.ib_SD = ib_SD;
        this.truckNumber = truckNumber;
    }

    public int getDeliveriesCompleted()
    {
        return deliveriesCompleted;
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
                Thread.sleep(random.nextInt(10*1000));
                //Thread.sleep(random.nextInt(1));
                //TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(0, 1000));
                currentOrder.setDelivered();
                System.out.println(currentOrder);
                deliveriesCompleted++;
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        notifyAll();
        System.out.println("Truck "+truckNumber+": No more deliveries!");


        /*
        if(ib_SD.isUpstreamFinished() && ib_SD.isBufferEmpty())
        {
            notifyAll();
            System.out.println("Truck "+truckNumber+": No more deliveries!");
        }

        try
        {
            //Wait until the buffer is at 4 or finished being processed
            while(!(ib_SD.getBufferSize() < 4) && !ib_SD.isUpstreamFinished())
            {
                wait();
            }

            ib_SD.drainQueueTo(deliveries);
            Order currentOrder;
            while((currentOrder = deliveries.take()) != null)
            {
                currentOrder.setDeliveryTruck(this);
                Thread.sleep(random.nextInt(10*1000));
                currentOrder.setDelivered();
                System.out.println(currentOrder);
                deliveriesCompleted++;
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

         */
    }

    public int getTruckNumber()
    {
        return truckNumber;
    }
}
