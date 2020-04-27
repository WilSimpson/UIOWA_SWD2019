public class Person
{
    private int age;
    private int height;
    private String name;

    public Person(int myAge, int myHeight, String myName)
    {
        age = myAge;
        height = myHeight;
        name = myName;
    }

    public void sayHello()
    {
        System.out.println("Hello");
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
