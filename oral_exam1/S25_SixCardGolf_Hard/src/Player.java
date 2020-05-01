public class Player
{
    private static int TOTAL_PLAYERS = 0;

    private final int id;

    private Hand hand;

    public Player()
    {
        this(null);
    }

    public Player(Hand hand)
    {
        id = TOTAL_PLAYERS;
        TOTAL_PLAYERS++;

        this.hand = hand;
    }

    public void setHand(Hand hand)
    {
        this.hand = hand;
    }

    public Hand getHand()
    {
        return hand;
    }

    public int getID()
    {
        return id;
    }

    public static void resetNumTotalPlayers()
    {
        TOTAL_PLAYERS = 0;
    }
}

