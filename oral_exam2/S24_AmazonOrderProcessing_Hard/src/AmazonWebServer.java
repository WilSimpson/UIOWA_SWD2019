import java.util.Arrays;
import java.util.LinkedList;

public class AmazonWebServer extends Node
{
    private CSVReader csvReader;

    private static final int ADDRESS_CSV_INDEX  = 0;
    private static final int CITY_CSV_INDEX     = 1;
    private static final int STATE_CSV_INDEX    = 2;
    private static final int ZIP_CSV_INDEX      = 3;
    private static final int NAME_CSV_INDEX     = 4;
    private static final int ITEM_CSV_INDEX     = 5;
    private static final int CATEGORY_CSV_INDEX = 6;

    public AmazonWebServer(Buffer ob_SC1, Buffer ob_SC2, String inputFileName)
    {
        super(null, new Buffer[]{ob_SC1, ob_SC2});
        this.csvReader = new CSVReader(inputFileName, ",", true);
    }

    @Override
    public void doOperations()
    {
        while(!csvReader.finishedReading())
        {
            String[] orderInfo = csvReader.getNextLine();
            if(orderInfo != null)
            {
                Order currentOrder = new Order(
                        new Address(orderInfo[ADDRESS_CSV_INDEX],
                                orderInfo[CITY_CSV_INDEX],
                                orderInfo[STATE_CSV_INDEX],
                                orderInfo[ZIP_CSV_INDEX]),
                        orderInfo[NAME_CSV_INDEX],
                        orderInfo[ITEM_CSV_INDEX],
                        orderInfo[CATEGORY_CSV_INDEX]
                );

                if(orderInfo[CITY_CSV_INDEX].equals("Los Angeles")
                        || orderInfo[CITY_CSV_INDEX].equals("San Francisco")
                        || orderInfo[CITY_CSV_INDEX].equals("Seattle")
                        || orderInfo[CITY_CSV_INDEX].equals("Denver"))
                {
                    putBlocking(currentOrder, 0);
                }
                else
                {
                    putBlocking(currentOrder, 1);
                }
            }
        }
    }

    @Override
    public void doFinally()
    {
        System.out.println("AWS: Finished processing all orders");
        debugNode();
    }
}
