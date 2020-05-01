import java.util.concurrent.ArrayBlockingQueue;

/**
 * An ArrayBlockingQueue buffer that links two nodes together. An element is put or taken from the queue by accessing
 * the putBlocking(Order) and getBlocking() methods respectively. If there are any issues getting or setting items in
 * the buffer the thread will wait until notified.
 *
 * @param <T> Type of the buffer
 *
 * @author Wil Simpson
 */
public class Buffer<T>
{
    /**
     * Starting ID for a buffer
     */
    private static int CURRENT_BUFFER_ID = 0;

    /**
     * ID for the buffer
     */
    private final int bufferID;

    /**
     * Queue for the buffer
     */
    private ArrayBlockingQueue<T> queue;

    /**
     * If the upstream node is finished adding to the queue
     */
    private boolean upstreamFinished = false;

    /**
     * Creates a new queue with a size of 1
     */
    public Buffer()
    {
        this(1);
    }

    /**
     * Creates a new buffer with the given size and sets the id
     *
     * @param queueSize size of the buffer
     */
    public Buffer(int queueSize)
    {
        queue = new ArrayBlockingQueue<>(queueSize);
        bufferID = CURRENT_BUFFER_ID;
        CURRENT_BUFFER_ID++;
    }

    /**
     * Attempts to put the given item in the queue. The thread will wait until the queue has room and the item can be
     * put into the queue
     *
     * @param t Object to put in the queue
     */
    public synchronized void putBlocking(T t)
    {
        try
        {
            while(queue.remainingCapacity() == 0)
            {
                notifyAll();
                wait();
            }

            queue.put(t);
            notifyAll();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Gets the next item in the queue. If the queue is empty the thread will wait until it can get an item
     *
     * @return item retrieved from queue
     */
    public synchronized T getBlocking()
    {
        T t = null;
        try
        {
            while(queue.size() == 0)
            {
                if(upstreamFinished) return null;
                wait();
            }

            t = queue.poll();
            notifyAll();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        return t;
    }

    /**
     * Marks the buffer that the upstream buffer is finished processing
     */
    public synchronized void setUpstreamFinished()
    {
        upstreamFinished = true;
        notifyAll();
    }

    /**
     * Checks if the upstream is finished processing
     *
     * @return true if the upstream is finished processing
     */
    public synchronized boolean isUpstreamFinished()
    {
        return upstreamFinished;
    }

    /**
     * Checks if the queue is empty
     *
     * @return true if queue is empty
     */
    public synchronized boolean isEmpty()
    {
        return queue.size() == 0;
    }

    /**
     * Checks if the queue is full
     *
     * @return true if the queue is full
     */
    public synchronized boolean isFull()
    {
        return queue.remainingCapacity() == 0;
    }

    /**
     * Checks if the downstream buffer should keep processing. A downstream queue should only stop processing if the
     * upstream node is finished and teh queue size is 0.
     *
     * @return true if the downstream node should keep getting input
     */
    public synchronized boolean shouldContinueAcceptingInput()
    {
        if(upstreamFinished)
        {
            return queue.size() == 0;
        }

        return queue.size() != 0;
    }

    /**
     * String readable representation of a buffer containing its id id and size
     *
     * @return string representation
     */
    @Override
    public String toString()
    {
        return getClass().getCanonicalName()+"["+bufferID+"]("+queue.size()+")";
    }
}
