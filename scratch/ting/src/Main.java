public class Main
{
    public static void main(String[] args)
    {
        Person wil = new Person(23, 72, "Wil");
        Person ting = new Person(28,68, "Ting");

        System.out.println(wil+": "+wil.getName());
        System.out.println(ting+": "+ting.getName());

        wil.sayHello();
    }
}
