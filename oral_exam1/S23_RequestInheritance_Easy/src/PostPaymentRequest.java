import java.util.UUID;

/**
 * Represents a post payment request and requires a uuid, ip and payment to be created
 *
 * @authr Wil Simpson
 */
public class PostPaymentRequest extends PostRequest
{
    /**
     * Total number of PostPaymentRequest's made
     */
    private static int count = 0;

    /**
     * Payment for request
     */
    private Payment payment;

    /**
     * Creates a new post payment request given a uuid, ip and payment
     *
     * @param uuid uuid of request
     * @param ip ip for request
     * @param payment payment for request
     */
    public PostPaymentRequest(UUID uuid, String ip, Payment payment)
    {
        super(uuid, ip);

        this.payment = payment;
        count++;
    }

    /**
     * Gets the total number of PostPaymentRequest's made
     *
     * @return total number of PostPaymentRequest's made
     */
    public static int count()
    {
        return count;
    }

    /**
     * Gets the payment for the request
     *
     * @return payment for the request
     */
    public Payment getPayment()
    {
        return payment;
    }

    /**
     * Sets the payment for the request
     *
     * @param payment new payment for the request
     */
    public void setPayment(Payment payment)
    {
        this.payment = payment;
    }

    /**
     * Creates a more human-readable string
     *
     * @return human-readable string
     */
    @Override
    public String toString()
    {
        return super.toString() + "\n"
                + payment;
    }
}
