/**
 * A node consists of a single input buffer and 1 or more output buffers. A node starts processing when the run()
 * is called
 *
 * @param <T> Buffer type for the node
 *
 * @author Wil Simpson
 */
public abstract class Node<T> implements Runnable
{
    /**
     * Next ID for a new node
     */
    private static int NEXT_ID = 1;

    /**
     * Node ID
     */
    private final int nodeID;

    /**
     * Input buffers
     */
    private Buffer<T> inputBuffer;

    /**
     * Output buffers
     */
    private Buffer<T>[] outputBuffers;

    /**
     * Creates a new node and sets the ID
     *
     * @param inputBuffer input buffers
     * @param outputBuffers output buffers
     */
    public Node(Buffer<T> inputBuffer, Buffer<T>[] outputBuffers)
    {
        this.inputBuffer = inputBuffer;
        this.outputBuffers = outputBuffers;

        nodeID = NEXT_ID;
        NEXT_ID++;
    }

    /**
     * Loops calling the doOperations() until the upstream buffer is finished processing then calls the doFinally()
     * method and does the final closing operations for the node.
     */
    @Override
    public final void run()
    {
        if(inputBuffer != null)
        {
            while(!isUpstreamFinished())
                doOperations();

            while(!inputBuffer.isEmpty())
                doOperations();
        }
        else
        {
            doOperations();
        }


        //doOperations();
        doFinally();

        if(outputBuffers != null)
        {
            for(int i=0; i<outputBuffers.length; i++)
            {
                outputBuffers[i].setUpstreamFinished();
            }
        }
    }

    /**
     * Operations the node should be making
     */
    public abstract void doOperations();

    /**
     * Final operations of the node
     */
    public abstract void doFinally();

    /**
     * Calls put blocking for output buffer
     *
     * @param t item to add to buffer
     * @param bufferIndex index for buffer to add to
     */
    public synchronized void putBlocking(T t, int bufferIndex)
    {
        outputBuffers[bufferIndex].putBlocking(t);
    }

    /**
     * Calls put blocking for the default output buffer
     *
     * @param t item to put in buffer
     */
    public synchronized void putBlocking(T t)
    {
        putBlocking(t, 0);
    }

    /**
     * Gets an item from the input buffer
     *
     * @return item from input buffer
     */
    public synchronized T getBlocking()
    {
        if(inputBuffer == null) return null;
        return inputBuffer.getBlocking();
    }

    /**
     * Gets the input buffer
     *
     * @return input buffer
     */
    public Buffer<T> getInputBuffer()
    {
        return inputBuffer;
    }

    public boolean isOutputBufferFull(int i)
    {
        return outputBuffers[i].isFull();
    }

    /**
     * Checks if the upstream node is finished. If there is no upstream it returns false.
     *
     * @return if the upstream node is finished
     */
    public synchronized boolean isUpstreamFinished()
    {
        if(inputBuffer == null) return false;
        return inputBuffer.isUpstreamFinished();
    }

    /**
     * Creates a human readable string name for the node containing the class name and id of the node
     *
     * @return node string representation
     */
    @Override
    public String toString()
    {
        return getClass().getCanonicalName()+"["+nodeID+"]";
    }
}
