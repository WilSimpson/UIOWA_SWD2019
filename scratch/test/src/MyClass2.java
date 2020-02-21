import java.util.Scanner;

public class MyClass2
{
    public static void main(String[] args)
    {
        System.out.println((new Scanner(System.in)).nextLine().replaceAll("\"", "/\""));
    }
}
