import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AmazonOrderProcessor
{


    public static void main(String[] args)
    {
        OrderBuffer aws_sc1 = new OrderBuffer(20);
        OrderBuffer aws_sc2 = new OrderBuffer(20);
        OrderBuffer sc1_s1 = new OrderBuffer(20);
        OrderBuffer sc1_s2 = new OrderBuffer(20);
        OrderBuffer sc2_s1 = new OrderBuffer(20);
        OrderBuffer sc2_s2 = new OrderBuffer(20);
        OrderBuffer s1_sd1 = new OrderBuffer(20);
        OrderBuffer s2_sd1 = new OrderBuffer(20);
        OrderBuffer s1_sd2 = new OrderBuffer(20);
        OrderBuffer s2_sd2 = new OrderBuffer(20);
        OrderBuffer sd1_dt1 = new OrderBuffer(4);
        OrderBuffer sd1_dt2 = new OrderBuffer(4);
        OrderBuffer sd2_dt1 = new OrderBuffer(4);
        OrderBuffer sd2_dt2 = new OrderBuffer(4);

        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new AmazonWebServer(aws_sc1, aws_sc2, "/home/wil/IdeaProjects/wsimpson_swd/oral_exam2/S24_AmazonOrderProcessing_Hard/src/S24_AmazonOrderProcessing_OrdersFile.csv"));
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

        try
        {
            Thread.sleep(1000);


        while(Order.getTotalOrdersCreated() < Order.getTotalOrdersDelivered())
        {
            Thread.sleep(100);
        }

        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
