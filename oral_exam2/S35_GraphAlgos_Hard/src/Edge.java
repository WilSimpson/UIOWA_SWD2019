import java.util.ArrayList;
import java.util.List;

public class Edge<T>
{
    private boolean directed;
    private SimpleVertex<T> start;
    private SimpleVertex<T> end;

    public Edge(SimpleVertex<T> start, SimpleVertex<T> end, boolean directed)
    {
        this.start = start;
        this.end = end;
        this.directed = directed;
    }

    public SimpleVertex<T> getNode()
    {
        return start;
    }

    public SimpleVertex<T> getOtherNode(SimpleVertex<T> vert)
    {
        if(vert.equals(start))
        {
            return end;
        }
        else if(vert.equals(end))
        {
            return start;
        }

        return null;
    }

    public SimpleVertex<T> getStart()
    {
        return start;
    }

    public void setStart(SimpleVertex<T> start)
    {
        this.start = start;
    }

    public SimpleVertex<T> getEnd()
    {
        return end;
    }

    public void setEnd(SimpleVertex<T> end)
    {
        this.end = end;
    }

    public boolean isDirected()
    {
        return directed;
    }

    public void setDirected(boolean isDirected)
    {
        directed = isDirected;
    }

    public boolean contains(SimpleVertex<T> vert)
    {
        if(vert == null) return false;

        return vert.equals(start) || vert.equals(end);
    }

    public boolean equals(Edge<T> other)
    {
        if(directed)
            return start.equals(other.getStart()) && end.equals(other.getEnd());

        return !(start.equals(other.getStart()) && end.equals(other.getEnd())
                || start.equals(other.getEnd()) && end.equals(other.getStart()));
    }

    @Override
    public String toString()
    {
        //return "E{"+start.toString()+"}";
        return String.format("E{%s,%s,%s}", start.toString(), end.toString(), (directed ? "T" : "F"));
    }
}