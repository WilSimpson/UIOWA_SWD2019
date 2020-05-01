import java.security.SecureRandom;
import java.util.LinkedList;

/**
 * A delivery truck takes input from a buffer and puts it in a delivery queue. Once a delivery queue size is exactly 4
 * the truck will start to deliver the orders. If the upstream node is finished the truck will deliver anything in
 * their queue and close. The truck will sleep a random amount of time between 0 - 10 seconds between each delivery.
 *
 * @author Wil Simpson
 */
public class DeliveryTruck extends Node<Order>
{
    /**
     * Number generator to generate random sleep times
     */
    private SecureRandom random = new SecureRandom();

    /**
     * Number of the truck
     */
    private int truckNumber;

    /**
     * Queue of deliveries waiting to be processed
     */
    private LinkedList<Order> waitingDeliveries = new LinkedList<>();

    /**
     * Creates a new delivery truck
     *
     * @param ib_SD input buffer from shipping dock
     * @param truckNumber truck number
     */
    public DeliveryTruck(Buffer<Order> ib_SD, int truckNumber)
    {
        super(ib_SD, null);
        this.truckNumber = truckNumber;
    }

    /**
     * Processes orders from the input buffer. If there are exactly 4 waiting deliveries in the trucks queue he will
     * deliver them.
     */
    @Override
    public void doOperations()
    {
        while(!getInputBuffer().isEmpty())
        {
            Order currentOrder = getBlocking();
            currentOrder.setDeliveryTruck(this);
            waitingDeliveries.add(currentOrder);

            if(waitingDeliveries.size() == 4)
            {
                deliver();
            }
        }
    }

    /**
     * The truck will make any deliveries and notify that it has finished making all deliveries
     */
    @Override
    public void doFinally()
    {
        if(hasDelivery()) deliver();
        System.out.println("Truck "+truckNumber+": No more deliveries!");
    }

    /**
     * Delivers all orders in the delivery queue
     */
    private synchronized void deliver()
    {
        while(hasDelivery())
        {
           try
           {
               Order currentOrder = waitingDeliveries.pop();

                Thread.sleep(random.nextInt(10*1000));

                currentOrder.setDelivered();
                System.out.println(currentOrder);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks whether the current delivery queue is not empty
     *
     * @return true if the delivery queue is larger than 0
     */
    public synchronized boolean hasDelivery()
    {
        return waitingDeliveries.size() > 0;
    }

    /**
     * Gets the truck number
     *
     * @return truck number
     */
    public int getTruckNumber()
    {
        return truckNumber;
    }
}
