public class Payment
{
    private String senderName;
    private String receiverName;
    private int amount;

    public Payment(String senderName, int amount, String receiverName)
    {
        this.senderName = senderName;
        this.amount = amount;
        this.receiverName = receiverName;
    }


    public String getSenderName()
    {
        return senderName;
    }

    public void setSenderName(String senderName)
    {
        this.senderName = senderName;
    }

    public String getReceiverName()
    {
        return receiverName;
    }

    public void setReceiverName(String receiverName)
    {
        this.receiverName = receiverName;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    @Override
    public String toString()
    {
        return "Payment Data\n"
              +"Payment sender: "+senderName+"\n"
              +"Payment amount: $"+amount+"\n"
              +"Payment receiver: "+receiverName;
    }
}
