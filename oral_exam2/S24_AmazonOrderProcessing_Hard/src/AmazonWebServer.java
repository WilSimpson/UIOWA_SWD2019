public class AmazonWebServer implements Runnable
{
    private CSVReader csvReader;

    private OrderBuffer ob_SC1;
    private OrderBuffer ob_SC2;

    private static final int ADDRESS_CSV_INDEX  = 0;
    private static final int CITY_CSV_INDEX     = 1;
    private static final int STATE_CSV_INDEX    = 2;
    private static final int ZIP_CSV_INDEX      = 3;
    private static final int NAME_CSV_INDEX     = 4;
    private static final int ITEM_CSV_INDEX     = 5;
    private static final int CATEGORY_CSV_INDEX = 6;

    public AmazonWebServer(OrderBuffer ob_SC1, OrderBuffer ob_SC2, String inputFileName)
    {
        this.ob_SC1 = ob_SC1;
        this.ob_SC2 = ob_SC2;
        this.csvReader = new CSVReader(inputFileName, ",", true);
    }

    @Override
    public synchronized void run()
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

                switch(orderInfo[CITY_CSV_INDEX])
                {
                    case "Los Angeles":
                    case "San Francisco":
                    case "Seattle":
                    case "Denver":
                        ob_SC1.putBlocking(currentOrder);
                        break;

                    default:
                        ob_SC2.putBlocking(currentOrder);
                }
            }
        }
        ob_SC1.setUpstreamFinished();
        ob_SC2.setUpstreamFinished();
        notifyAll();
        System.out.println("AWS: Finished processing all orders");
    }
}
