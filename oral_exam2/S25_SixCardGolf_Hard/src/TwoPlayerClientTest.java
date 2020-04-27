import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TwoPlayerClientTest
{
    public static void main(String[] args)
    {
        TwoPlayerClient twoPlayerClient = new TwoPlayerClient();
        twoPlayerClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(twoPlayerClient);
        es.shutdown();
    }
}
