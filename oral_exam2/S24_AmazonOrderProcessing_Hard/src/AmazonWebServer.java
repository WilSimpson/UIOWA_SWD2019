/**
 * Reads from a given CSV file with order information and sends it to an output buffer
 *
 * @author Wil Simpson
 */
public class AmazonWebServer extends Node<Order>
{
    /**
     * CSVReader used to process file
     */
    private CSVReader csvReader;

    /**
     * Index of address in CSV file
     */
    private static final int ADDRESS_CSV_INDEX  = 0;

    /**
     * Index of city in CSV file
     */
    private static final int CITY_CSV_INDEX     = 1;

    /**
     * Index of state in CSV file
     */
    private static final int STATE_CSV_INDEX    = 2;

    /**
     * Index of zip in CSV file
     */
    private static final int ZIP_CSV_INDEX      = 3;

    /**
     * Index of name in CSV file
     */
    private static final int NAME_CSV_INDEX     = 4;

    /**
     * Index of item in CSV file
     */
    private static final int ITEM_CSV_INDEX     = 5;

    /**
     * Index of category in CSV file
     */
    private static final int CATEGORY_CSV_INDEX = 6;

    /**
     * Initializes the node
     *
     * @param ob_SC1 output buffer to shipping center 1
     * @param ob_SC2 output buffer to shipping center 2
     * @param inputFile path to the csv file to process
     */
    public AmazonWebServer(Buffer<Order> ob_SC1, Buffer<Order> ob_SC2, String inputFile)
    {
        super(null, new Buffer[]{ob_SC1, ob_SC2});
        this.csvReader = new CSVReader(inputFile, true);
    }

    /**
     * Processes all entries in the CSV, creates an order for each entry and sends it to the correct output buffer.
     */
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

    /**
     * Prints the AWS is done processing all orders
     */
    @Override
    public void doFinally()
    {
        System.out.println("AWS: Finished processing all orders");
    }
}
