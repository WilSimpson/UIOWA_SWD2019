import java.util.List;

public class WeightedEdge<T> extends Edge<T> implements Comparable<WeightedEdge<T>>
{
    private int weight;

    public WeightedEdge(SimpleVertex<T> v1, SimpleVertex<T> v2, boolean directed, int weight)
    {
        super(v1, v2, directed);

        this.weight = weight;
    }

    public int getWeight()
    {
        return weight;
    }

    public boolean equals(WeightedEdge<T> other)
    {
        if(super.equals(other)) return false;

        return weight == other.getWeight();
    }

    @Override
    public int compareTo(WeightedEdge<T> other)
    {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString()
    {
        String old = "W"+super.toString();
        //return old;
        return String.format(old.substring(0,old.length()-1)+",%d}", weight);
    }
}
