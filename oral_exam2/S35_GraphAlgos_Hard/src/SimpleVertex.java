public class SimpleVertex<T>
{
    private T value;

    public SimpleVertex(T value)
    {
        this.value = value;
    }

    public T getValue()
    {
        return value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }

    public boolean equals(SimpleVertex<T> other)
    {
        return getValue().equals(other.getValue());
    }

    @Override
    public String toString()
    {
        return String.format("SV(%s)", value);
    }

}
