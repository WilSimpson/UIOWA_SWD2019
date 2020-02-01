import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChangeComputation
{


    public static void main(String[] args)
    {
        /**
        //Used to grab data from the console
        Scanner scanner = new Scanner(System.in);

        //Used to format the number input into a decimal
        DecimalFormat formatter = new DecimalFormat("0.00");

        System.out.println("Enter the price of the item without any commas.\n");
        float itemPrice = 0;
        while(itemPrice <= 0)
        {
            try
            {
                System.out.print("Enter item price: $");
                itemPrice = Float.parseFloat(scanner.nextLine());
            }
            catch(NumberFormatException e)
            {
                System.out.println("ERROR: MUST ENTER A NUMBER!");
            }
        }

        itemPrice = Float.parseFloat(formatter.format(itemPrice));

         */

        Drawer drawer = new Drawer();
    }
}
