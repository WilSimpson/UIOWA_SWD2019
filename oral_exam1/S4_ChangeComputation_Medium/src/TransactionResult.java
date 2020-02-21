/**
 * Result of a transaction
 *
 * @author Wil Simpson
 */
public enum TransactionResult
{
    //The transaction ended in success
    SUCCESS,

    //The transaction failed due to not enough money was given to pay
    INSUFFICIENT_FUNDS,

    //The transaction failed due to not enough change available in a drawer
    INSUFFICIENT_CHANGE
}
