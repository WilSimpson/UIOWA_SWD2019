import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Simulates processing Amazon Orders from the S24_AmazonOrderProcessing_OrdersFile.csv file. All nodes communicate through
 * their given buffers and all nodes run until the input buffer is empty and upstream node is finished. The example
 * processing is routed in the below figure. A buffer is represented by a line in the figure. The node higher up in the
 * gride is the input buffer for the lower node. The Amazon Web Server input is the CSV file while the Trucks do not
 * have any output buffers and print to the terminal directly. All nodes are independent and only are connected
 * through their buffers. Nodes don't start processing until the run() method is called.
 *
 *                                 Amazon Web Server
 *                                /                 \
 *                   Shipping Center              Shipping Center
 *                   /            \               /            \
 *               Section1      Section2       Section1      Section2
 *                     \        /                   \        /
 *                   Shipping Dock                Shipping Dock
 *                   /           \                /           \
 *                Truck1      Truck2          Truck1        Truck2
 *
 * @author Wil Simpson
 */
public class AmazonOrderProcessor
{
    public static void main(String[] args)
    {
        //All buffers for the simulation name based on relation to upstream node
        //Before underscore is output from upstream
        //After underscore is input from upstream
        Buffer<Order> aws_sc1 = createBuffer();
        Buffer<Order> aws_sc2 = createBuffer();
        Buffer<Order> sc1_s1  = createBuffer();
        Buffer<Order> sc1_s2  = createBuffer();
        Buffer<Order> sc2_s1  = createBuffer();
        Buffer<Order> sc2_s2  = createBuffer();
        Buffer<Order> s1_sd1  = createBuffer();
        Buffer<Order> s2_sd1  = createBuffer();
        Buffer<Order> s1_sd2  = createBuffer();
        Buffer<Order> s2_sd2  = createBuffer();
        Buffer<Order> sd1_dt1 = createBuffer();
        Buffer<Order> sd1_dt2 = createBuffer();
        Buffer<Order> sd2_dt1 = createBuffer();
        Buffer<Order> sd2_dt2 = createBuffer();

        //Starts all the nodes. Order of execution does not matter
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new AmazonWebServer(aws_sc1, aws_sc2, "S24_AmazonOrderProcessing_OrdersFile.csv"));
        es.execute(new ShippingCenter(aws_sc1, sc1_s1, sc1_s2, 1));
        es.execute(new ShippingCenter(aws_sc2, sc2_s1, sc2_s2, 2));
        es.execute(new Section(sc1_s1, s1_sd1, 1));
        es.execute(new Section(sc1_s2, s2_sd1, 2));
        es.execute(new Section(sc2_s1, s1_sd2, 1));
        es.execute(new Section(sc2_s2, s2_sd2,2));
        es.execute(new ShippingDock(s1_sd1, sd1_dt1, sd1_dt2));
        es.execute(new ShippingDock(s2_sd1, sd1_dt1, sd1_dt2));
        es.execute(new ShippingDock(s1_sd2, sd2_dt1, sd2_dt2));
        es.execute(new ShippingDock(s2_sd2, sd2_dt1, sd2_dt2));
        es.execute(new DeliveryTruck(sd1_dt1, 1));
        es.execute(new DeliveryTruck(sd2_dt1, 2));
        es.execute(new DeliveryTruck(sd1_dt2, 1));
        es.execute(new DeliveryTruck(sd2_dt2, 2));
        es.shutdown();
    }

    /**
     * Creates a new buffer with an abstract size of 5
     *
     * @return new buffer of size 5
     */
    public static Buffer<Order> createBuffer()
    {
        return new Buffer<Order>(25);
    }
}
