import java.util.Collections;
import java.util.LinkedList;

public class Deck
{
    public LinkedList<Card> deck = new LinkedList<>();

    public Deck()
    {
        for(Card.CardSuite suite : Card.CardSuite.values())
            for(Card.CardNumber number : Card.CardNumber.values())
                deck.add(new Card(number, suite));
    }

    public Deck newDeck()
    {
        deck.clear();

        for(Card.CardSuite suite : Card.CardSuite.values())
            for(Card.CardNumber number : Card.CardNumber.values())
                deck.add(new Card(number, suite));

        return this;
    }

    public Deck shuffle()
    {
        Collections.shuffle(deck);

        return this;
    }

    public Card[] drawHand()
    {
        int numCardsPerHand = Hand.getNumberCards();
        Card[] hand = new Card[numCardsPerHand];

        for(int i=0; i<hand.length; i++)
        {
            hand[i] = drawCard();
        }

        return hand;
    }

    public Card drawCard()
    {
        return deck.pop();
    }

    public int cardsLeft()
    {
        return deck.size();
    }
}
