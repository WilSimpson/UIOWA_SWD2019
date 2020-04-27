public class Card
{
    private CardSuite suite;
    private CardNumber number;
    private boolean isVisible = false;

    public Card(CardNumber number, CardSuite suite)
    {
        this.suite = suite;
        this.number = number;
    }

    public CardSuite getSuite()
    {
        return suite;
    }

    public CardNumber getNumber()
    {
        return number;
    }

    public boolean isVisible()
    {
        return isVisible;
    }

    public void setVisible()
    {
        isVisible = true;
    }

    @Override
    public String toString()
    {

        return isVisible ? ""+number+suite : "";
    }

    public enum CardSuite
    {
        HEARTS("♥"),
        DIAMONDS("♦"),
        SPADES("♠"),
        CLUBS("♣");

        private String string;

        CardSuite(String string)
        {
            this.string = string;
        }

        @Override
        public String toString()
        {
            return string;
        }
    }

    public enum CardNumber
    {
        ACE("A", -1),
        ONE("1", 1),
        TWO("2", 2),
        THREE("3", 3),
        FOUR("4", 4),
        FIVE("5", 5),
        SIX("6", 6),
        SEVEN("7", 7),
        EIGHT("8", 8),
        NINE("9", 9),
        TEN("10", 10),
        JACK("J", 10),
        QUEEN("Q", 10),
        KING("K", 0);

        private String string;
        private int value;

        CardNumber(String string, int value)
        {
            this.string = string;
            this.value = value;
        }

        public int getValue()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return string;
        }
    }
}
