import java.security.SecureRandom;
import java.util.concurrent.ArrayBlockingQueue;

public class DeliveryTruck implements Runnable
{
    private SecureRandom random = new SecureRandom();

    private OrderBuffer ib_SD;
    private int truckNumber;

    private ArrayBlockingQueue<Order> deliveryQueue = new ArrayBlockingQueue<>(10);

    ArrayBlockingQueue<Order> deliveries = new ArrayBlockingQueue<>(4);

    public DeliveryTruck(OrderBuffer ib_SD, int truckNumber)
    {
        this.ib_SD = ib_SD;
        this.truckNumber = truckNumber;
    }

    @Override
    public synchronized void run()
    {

        while(ib_SD.shouldContinueAcceptingInput() && !deliveryQueue.isEmpty())
        {
            Order currentOrder = ib_SD.getBlocking();
            currentOrder.setDeliveryTruck(this);
            deliveryQueue.add(currentOrder);

            if(deliveryQueue.size() == 4 || ib_SD.isBufferEmpty())
            {
                while(deliveryQueue.peek() != null)
                {
                    try
                    {
                        currentOrder = deliveryQueue.take();

                        //Thread.sleep(random.nextInt(10*1000));
                        Thread.sleep(random.nextInt(1));
                        //TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(0, 1000));

                        currentOrder.setDelivered();
                        System.out.println(currentOrder);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

        notifyAll();
        System.out.println("Truck "+truckNumber+": No more deliveries!");
    }

    public int getTruckNumber()
    {
        return truckNumber;
    }
}
