public class Edge<T>
{
    private T start;
    private T end;

    public Edge(T start, T end)
    {
        this.start = start;
        this.end = end;
    }


    public T getStart()
    {
        return start;
    }

    public void setStart(T start)
    {
        this.start = start;
    }

    public T getEnd()
    {
        return end;
    }

    public void setEnd(T end)
    {
        this.end = end;
    }
}
