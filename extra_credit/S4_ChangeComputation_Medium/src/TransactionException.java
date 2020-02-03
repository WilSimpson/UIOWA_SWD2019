public class TransactionException extends Exception
{
    private TransactionResult result;

    public TransactionException(TransactionResult result)
    {
        this.result = result;
    }

    public TransactionResult getResult()
    {
        return result;
    }
}
