import java.util.ArrayList;

public final class GameManager
{
    private GameState gameState;

    private int maxNumberPlayers;
    private ArrayList<Player> players;
    private Deck deck;

    /**
     * Index is the id of a player and the value is the number of cards they've chosen to start with
     */
    private int[] numberCardsChosen;

    public GameManager(int maxNumberPlayers)
    {
        this.maxNumberPlayers = maxNumberPlayers;
        numberCardsChosen = new int[maxNumberPlayers];
        gameState = GameState.WAITING_FOR_PLAYERS;
        players = new ArrayList<>();
        deck = new Deck().shuffle();
    }

    /**
     * Adds a player to the game and immediately starts the game if the total number of players required is met
     *
     * @param p Player to add to the game
     * @throws ServerException Thrown if the game is already started since a player cannot be added mid game
     */
    public void addPlayer(Player p) throws ServerException
    {
        if(players.size() >= maxNumberPlayers) throw new ServerException("Max number of players reached!");

        players.add(p);
    }

    public Card playerChoseShowCard(int playerID, int row, int col)
    {
        Player p = getPlayer(playerID);
        Card cardShown = p.getHand().getCard(row, col);
        cardShown.setVisible();
        numberCardsChosen[playerID]++;

        if(numberCardsChosen[playerID] >= 2)
        {
            if(getGameState().incrementID() >= maxNumberPlayers)
            {
                gameState.restartState();
                gameState = GameState.PLAYER_MAKE_MOVE;
            }
        }

        return cardShown;
    }

    public Player getPlayer(int id)
    {
        for(Player p : players)
        {
            if(p.getID() == id)
                return p;
        }

        return null;
    }

    public void initializeGame()
    {
        gameState = GameState.INITIALIZING;
        deck = new Deck().shuffle();

        for(Player currentPlayer : players)
        {
            currentPlayer.setHand(new Hand(deck.drawHand()));
        }
        gameState = GameState.PLAYER_CHOOSE_SHOW_CARDS;
    }

    public int getPlayerIDCurrentTurn()
    {
        return gameState.getCurrentID();
    }

    public int getMaxNumberPlayers()
    {
        return maxNumberPlayers;
    }

    public void reset()
    {
        players.clear();
        Player.resetNumTotalPlayers();
        GameState.reset();
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public int getNumberConnectedPlayers()
    {
        return players.size();
    }

    /**
     * The player chooses to start a game or join a game
     * The game waits for the required number of players to connect
     * The game then starts initializing by setting up all players, deck, hands, ect.
     * Then the game lets the players choose which cards they would like to show.
     * Then the game lets each player make their moves in order.
     * After the game has come to a conclusion the game ends.
     */
    public enum GameState
    {
        WAITING_FOR_PLAYERS(),
        INITIALIZING(),
        PLAYER_CHOOSE_SHOW_CARDS(),
        PLAYER_MAKE_MOVE(),
        GAME_END();

        /**
         * Part in the current sequence
         */
        private int currentID = 0;

        /**
         * Sets the number of sequences
         *
         * @param num number of sequences for the state
         */
        public void setCurrentID(int num)
        {
            currentID = num;
        }

        public int incrementID()
        {
            currentID++;

            return currentID;
        }

        /**
         * Gets the current sequence for the state
         *
         * @return current sequence
         */
        public int getCurrentID()
        {
            return currentID;
        }

        /**
         * Sets the current sequence to 0
         */
        public void restartState()
        {
            currentID = 0;
        }

        /**
         * Resets all GameState's to have no sequences and the current sequence to 0
         */
        public static void reset()
        {
            for(GameState currentState : values())
            {
                currentState.restartState();
            }
        }
    }
}
