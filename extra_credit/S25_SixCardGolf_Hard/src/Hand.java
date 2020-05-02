public class Hand
{
    public static final int NUM_ROWS = 2;
    public static final int NUM_COLS = 3;

    private Card[][] hand = new Card[NUM_ROWS][NUM_COLS];

    public Hand(Card[] cards)
    {
        if(cards.length != 6) throw new IllegalArgumentException("Hand must be given exactly 6 cards");


        for(int i=0,j=0; j<NUM_ROWS; j++)
            for(int k=0; k<NUM_COLS; k++,i++)
                hand[j][k] = cards[i];
    }

    public Card getCard(int row, int col)
    {
        if(row >= NUM_ROWS
        || col >= NUM_COLS
        || row < 0
        || col < 0) throw new IllegalArgumentException("Row must be: 0 < row < "+NUM_ROWS+". And col must be: 0 < col < "+NUM_COLS+".");

        return hand[row][col];
    }

    public static int getNumberCards()
    {
        return NUM_ROWS*NUM_COLS;
    }
}
