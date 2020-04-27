import java.util.concurrent.ArrayBlockingQueue;

public class OrderBuffer
{
    private ArrayBlockingQueue<Order> queue;

    private boolean upstreamFinished = false;

    public OrderBuffer()
    {
        this(1);
    }

    public OrderBuffer(int queueSize)
    {
        queue = new ArrayBlockingQueue<>(queueSize);
    }

    public synchronized void putBlocking(Order order)
    {
        try
        {
            while(queue.remainingCapacity() == 0)
            {
                wait();
            }

            queue.add(order);
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
            while(queue.size() == 0 || !upstreamFinished)
            {
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

    public synchronized boolean isBufferEmpty()
    {
        return queue.size() == 0;
    }

    public synchronized int getBufferSize()
    {
        return queue.size();
    }

    public synchronized boolean isBufferFull()
    {
        return queue.remainingCapacity() == 0;
    }

    public synchronized boolean shouldContinueAcceptingInput()
    {
        return !(isUpstreamFinished() && isBufferEmpty());
    }
}
