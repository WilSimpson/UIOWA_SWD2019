import java.util.concurrent.ArrayBlockingQueue;

public class Buffer
{
    private static int CURRENT_BUFFER_ID = 0;
    private final int bufferID;
    private ArrayBlockingQueue<Order> queue;

    private boolean upstreamFinished = false;

    public Buffer()
    {
        this(1);
    }

    public Buffer(int queueSize)
    {
        queue = new ArrayBlockingQueue<>(queueSize);
        bufferID = CURRENT_BUFFER_ID;
        CURRENT_BUFFER_ID++;
    }

    public synchronized void putBlocking(Order order)
    {
        try
        {
            while(queue.remainingCapacity() == 0)
            {
                notifyAll();
                wait();
            }

            queue.put(order);
            notifyAll();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public synchronized Order getBlocking()
    {
        Order order = null;
        try
        {
            while(queue.size() == 0)
            {
                notifyAll();
                wait();
            }

            order = queue.take();
            notifyAll();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        return order;
    }

    public synchronized void setUpstreamFinished()
    {
        upstreamFinished = true;
        notifyAll();
    }

    public synchronized boolean isUpstreamFinished()
    {
        return upstreamFinished;
    }

    public synchronized boolean isEmpty()
    {
        return queue.size() == 0;
    }

    public synchronized int size()
    {
        return queue.size();
    }

    public synchronized boolean isFull()
    {
        return queue.remainingCapacity() == 0;
    }

    public synchronized boolean shouldContinueAcceptingInput()
    {
        if(upstreamFinished)
        {
            return queue.size() == 0;
        }

        return queue.size() != 0;
    }
    
    @Override
    public String toString()
    {
        return getClass().getCanonicalName()+"["+bufferID+"]("+queue.size()+")";
    }
}
