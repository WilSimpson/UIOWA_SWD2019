import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AmazonOrderProcessor
{


    public static void main(String[] args)
    {
        Buffer aws_sc1 = createBuffer();
        Buffer aws_sc2 = createBuffer();
        Buffer sc1_s1  = createBuffer();
        Buffer sc1_s2  = createBuffer();
        Buffer sc2_s1  = createBuffer();
        Buffer sc2_s2  = createBuffer();
        Buffer s1_sd1  = createBuffer();
        Buffer s2_sd1  = createBuffer();
        Buffer s1_sd2  = createBuffer();
        Buffer s2_sd2  = createBuffer();
        Buffer sd1_dt1 = createBuffer();
        Buffer sd1_dt2 = createBuffer();
        Buffer sd2_dt1 = createBuffer();
        Buffer sd2_dt2 = createBuffer();

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

    public static Buffer createBuffer()
    {
        return new Buffer(25);
    }
}
