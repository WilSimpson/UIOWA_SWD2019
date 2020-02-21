/**
 * Exception caused by a transaction
 *
 * @author Wil Simpson
 */
public class TransactionException extends Exception
{
    /**
     * Result of the transaction
     */
    private final TransactionResult result;

    /**
     * Creates an exception given a transaction result
     *
     * @param result result of the transaction due to the exception
     */
    public TransactionException(TransactionResult result)
    {
        this.result = result;
    }

    /**
     * Get the result of the exception
     *
     * @return result of the exception
     */
    public TransactionResult getResult()
    {
        return result;
    }
}
