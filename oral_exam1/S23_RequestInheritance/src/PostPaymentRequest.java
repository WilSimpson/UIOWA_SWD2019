import java.util.UUID;

public class PostPaymentRequest extends PostRequest
{
    private static int count = 0;

    private Payment payment;

    public PostPaymentRequest(UUID uuid, String ip, Payment payment)
    {
        super(uuid, ip);

        this.payment = payment;
        count++;
    }

    public static int count()
    {
        return count;
    }

    public Payment getPayment()
    {
        return payment;
    }

    public void setPayment(Payment payment)
    {
        this.payment = payment;
    }

    @Override
    public String toString()
    {
        return super.toString()+"\n"
                +payment;
    }
}
