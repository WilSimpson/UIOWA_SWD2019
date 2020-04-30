public abstract class Node implements Runnable
{
    private static int NEXT_ID = 1;

    private final int nodeID;
    private Buffer inputBuffer;
    private Buffer[] outputBuffers;

    public Node(Buffer inputBuffer, Buffer[] outputBuffers)
    {
        this.inputBuffer = inputBuffer;
        this.outputBuffers = outputBuffers;

        nodeID = NEXT_ID;
        NEXT_ID++;
    }

    @Override
    public final synchronized void run()
    {
        while(!isUpstreamFinished())
        {
            doOperations();
        }
        doOperations();
        doFinally();
        closeNode();
    }

    public abstract void doOperations();

    public abstract void doFinally();

    public synchronized int numOutputBuffers()
    {
        return outputBuffers.length;
    }

    public synchronized boolean offer(int bufferIndex)
    {
        return outputBuffers[bufferIndex].isFull();
    }

    public synchronized void putBlocking(Order order, int bufferIndex)
    {
        outputBuffers[bufferIndex].putBlocking(order);
    }

    public synchronized void putBlocking(Order order)
    {
        putBlocking(order, 0);
    }

    public synchronized Order getBlocking()
    {
        if(inputBuffer == null) return null;
        return inputBuffer.getBlocking();
    }

    public Buffer getInputBuffer()
    {
        return inputBuffer;
    }

    public Buffer[] getOutputBuffers()
    {
        return outputBuffers;
    }

    public synchronized void closeNode()
    {
        if(outputBuffers != null)
        {
            for(int i=0; i<outputBuffers.length; i++)
            {
                outputBuffers[i].setUpstreamFinished();
            }
        }

        notifyAll();
    }

    public synchronized boolean isUpstreamFinished()
    {
        if(inputBuffer == null) return true;
        return inputBuffer.isUpstreamFinished();
    }

    public boolean shouldContinueProcessing()
    {
        if(inputBuffer == null) return false;
        return inputBuffer.shouldContinueAcceptingInput();
    }

    @Override
    public String toString()
    {
        return getClass().getCanonicalName()+"["+nodeID+"]";
    }

    public void debugNode()
    {
        String s = "In"+inputBuffer+", ";

        if(outputBuffers != null)
        {
            for(int i = 0; i < outputBuffers.length; i++)
            {
                s += "Out"+outputBuffers[i]+", ";
            }
        }

        System.out.println(String.format("%s: %s", toString(), s.substring(0,s.length()-2)));
    }
}
