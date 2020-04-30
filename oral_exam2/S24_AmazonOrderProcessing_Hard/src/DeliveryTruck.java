import java.security.SecureRandom;
import java.util.LinkedList;

public class DeliveryTruck extends Node
{
    private SecureRandom random = new SecureRandom();

    private int truckNumber;

    private int deliveriesCompleted = 0;

    private LinkedList<Order> waitingDeliveries = new LinkedList<>();

    public DeliveryTruck(Buffer ib_SD, int truckNumber)
    {
        super(ib_SD, null);
        this.truckNumber = truckNumber;
    }

    public int getDeliveriesCompleted()
    {
        return deliveriesCompleted;
    }

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

    @Override
    public void doFinally()
    {
        if(hasDelivery()) deliver();
        System.out.println("Truck "+truckNumber+": No more deliveries!");
        debugNode();
    }



    private synchronized void deliver()
    {
        while(hasDelivery())
        {
           try
           {
                Order currentOrder = waitingDeliveries.pop();

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

        //System.out.println("Truck "+truckNumber+": Finished deliveries.");
    }

    public synchronized boolean hasDelivery()
    {
        return waitingDeliveries.peek() != null;
    }

    public int getTruckNumber()
    {
        return truckNumber;
    }
}
