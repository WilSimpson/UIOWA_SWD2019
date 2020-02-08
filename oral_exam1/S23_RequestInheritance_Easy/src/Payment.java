/**
 * A payment consists of a sender, an amount and a receiver and represents a single payment/transaction.
 *
 * @author Wil Simpson
 */
public class Payment
{
    /**
     * Sender's name
     */
    private String senderName;

    /**
     * Receiver's name
     */
    private String receiverName;

    /**
     * Payment amount
     */
    private int amount;

    /**
     * Creates a payment given a sender, amount, and receiver
     * @param senderName
     * @param amount
     * @param receiverName
     */
    public Payment(String senderName, int amount, String receiverName)
    {
        this.senderName = senderName;
        this.amount = amount;
        this.receiverName = receiverName;
    }

    /**
     * Get the sender's name
     *
     * @return sender's name
     */
    public String getSenderName()
    {
        return senderName;
    }

    /**
     * Set the sender name
     *
     * @param senderName new sender's name
     */
    public void setSenderName(String senderName)
    {
        this.senderName = senderName;
    }

    /**
     * Get the receiver's name
     *
     * @return receiver's name
     */
    public String getReceiverName()
    {
        return receiverName;
    }

    /**
     * Set the receiver's name
     *
     * @param receiverName new receiver's name
     */
    public void setReceiverName(String receiverName)
    {
        this.receiverName = receiverName;
    }

    /**
     * Get the payment amount
     *
     * @return payment amount
     */
    public int getAmount()
    {
        return amount;
    }

    /**
     * Set the payment amount
     *
     * @param amount new payment amount
     */
    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    /**
     * Creates a more human-readable string
     *
     * @return human-readable string
     */
    @Override
    public String toString()
    {
        return "Payment Data\n"
                + "Payment sender: " + senderName + "\n"
                + "Payment amount: $" + amount + "\n"
                + "Payment receiver: " + receiverName;
    }
}
