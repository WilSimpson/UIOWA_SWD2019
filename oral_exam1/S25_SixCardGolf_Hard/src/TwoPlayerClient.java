import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

/**
 * Move panels into own class to reduce code reusage
 */
public class TwoPlayerClient extends JFrame implements Runnable
{
    private static final int CARD_WIDTH = 50;
    private static final int CARD_HEIGHT = 100;
    private static final int V_SPACING = 25;
    private static final int H_SPACING = 25;
    private static final int V_CARD_SPACING = 10;
    private static final int H_CARD_SPACING = 10;

    private JButton[][] playerCardButtons = new JButton[Hand.NUM_ROWS][Hand.NUM_COLS];
    private JButton[][] opponentCardButtons = new JButton[Hand.NUM_ROWS][Hand.NUM_COLS];

    private DatagramSocket socket;

    private JTextArea serverResponse = new JTextArea("");

    private int playerID;

    /**
     * First button is the discard. Second button is the top of the deck. Third button is the card that was just chosen
     * from the top of the deck.
     */
    private JButton[] deckButtons = new JButton[3];

    public TwoPlayerClient()
    {
        GridLayout clientGridLayout = new GridLayout(4,1);
        clientGridLayout.setHgap(H_SPACING);
        clientGridLayout.setVgap(V_SPACING);
        setLayout(clientGridLayout);

        int playerAreaWidth = (H_CARD_SPACING+CARD_WIDTH)*Hand.NUM_COLS;
        int playerAreaHeight = (V_CARD_SPACING+CARD_HEIGHT)*Hand.NUM_ROWS;
        int deckAreaHeight = CARD_HEIGHT;
        int deckAreaWidth = CARD_WIDTH+(V_CARD_SPACING+CARD_WIDTH)*deckButtons.length;

        this.setSize((H_SPACING*2)+playerAreaWidth, playerAreaHeight*2+deckAreaHeight);

        //The player's panel
        JPanel playerPanel = new JPanel();
        GridLayout playerGridLayout = new GridLayout(Hand.NUM_ROWS, Hand.NUM_COLS);
        playerGridLayout.setVgap(V_CARD_SPACING);
        playerGridLayout.setHgap(H_CARD_SPACING);
        playerPanel.setLayout(playerGridLayout);
        playerPanel.setPreferredSize(new Dimension(playerAreaWidth, playerAreaHeight));
        for(int row=0; row<Hand.NUM_ROWS; row++)
        {
            for(int col=0; col<Hand.NUM_COLS; col++)
            {
                JButton currentButton = new JButton("");
                currentButton.addActionListener(new PlayerCardActionListener(true, row, col));
                playerCardButtons[row][col] = currentButton;

                currentButton.setSize(CARD_WIDTH, CARD_HEIGHT);
                playerPanel.add(currentButton);
            }
        }

        //Deck Panel
        JPanel deckPanel = new JPanel();
        GridLayout deckGridLayout = new GridLayout(1, 3);
        deckGridLayout.setVgap(V_CARD_SPACING);
        deckGridLayout.setHgap(H_CARD_SPACING);
        deckPanel.setLayout(deckGridLayout);
        deckPanel.setPreferredSize(new Dimension(deckAreaHeight, deckAreaWidth));
        for(int i=0; i<deckButtons.length; i++)
        {
            JButton currentButton = new JButton("");
            currentButton.setEnabled(false);
            currentButton.addActionListener(new PlayerCardActionListener(false, 0, i));
            deckButtons[i] = currentButton;

            if(i == 3) deckButtons[i].setVisible(false);

            currentButton.setSize(CARD_WIDTH, CARD_HEIGHT);
            deckPanel.add(currentButton);
        }

        //Opponent's panel
        JPanel opponentPanel = new JPanel();
        GridLayout player2GridLayout = new GridLayout(Hand.NUM_ROWS, Hand.NUM_COLS);
        player2GridLayout.setVgap(V_CARD_SPACING);
        player2GridLayout.setHgap(H_CARD_SPACING);
        opponentPanel.setLayout(player2GridLayout);
        opponentPanel.setPreferredSize(new Dimension(playerAreaWidth, playerAreaHeight));
        for(int row=0; row<Hand.NUM_ROWS; row++)
        {
            for(int col=0; col<Hand.NUM_COLS; col++)
            {
                JButton currentButton = new JButton("");
                currentButton.setEnabled(false);
                opponentCardButtons[row][col] = currentButton;


                currentButton.setSize(CARD_WIDTH, CARD_HEIGHT);
                opponentPanel.add(currentButton);
            }
        }

        serverResponse.setEditable(false);
        serverResponse.setLineWrap(true);
        serverResponse.setWrapStyleWord(true);

        add(opponentPanel);
        add(deckPanel);
        add(playerPanel);
        add(serverResponse);

        setVisible(true);

        try
        {
            socket = new DatagramSocket();
        }
        catch (SocketException socketException)
        {
            socketException.printStackTrace();
            System.exit(1);
        }

        //c means a player is connecting, second param is the server id
        sendPacket("c,0");
    }

    @Override
    public Insets getInsets()
    {
        return new Insets(V_SPACING, H_SPACING, V_SPACING, H_SPACING);
    }

    public void waitForServerResponse()
    {

    }

    @Override
    public void run()
    {
        while(true)
        {
            String stringData = "";
            try
            {
                byte[] data = new byte[100];
                DatagramPacket packet = new DatagramPacket(data, data.length);
                socket.receive(packet);

                stringData = new String(packet.getData(), 0, packet.getLength());
                serverResponse.setText(stringData);

                String[] splitStringData = stringData.split(",");

                if(splitStringData[0].equals("ca"))
                {
                    playerID = Integer.parseInt(splitStringData[1]);
                    setTitle("Player "+playerID);
                    serverResponse.setText("Server accepted connection! You are Player "+playerID);
                    break;
                }
                else if(splitStringData[0].equals("ch"))
                {
                    serverResponse.setText("Choose 2 cards from your hand you want to see.");
                }
                else if(splitStringData[0].equals("e"))
                {
                    serverResponse.setText("ERROR: "+splitStringData[1]);
                }
                else
                {
                    int playerID = Integer.parseInt(splitStringData[1]);
                    int row = Integer.parseInt(splitStringData[3]);
                    int col = Integer.parseInt(splitStringData[4]);
                    String card = splitStringData[5];

                    //Card shown is in the player's hand
                    if(Integer.parseInt(splitStringData[2]) == 0)
                    {
                        if(this.playerID == playerID)
                        {
                            playerCardButtons[row][col].setText(card);
                        }
                        else
                        {
                            opponentCardButtons[row][col].setText(card);
                        }
                    }
                    //Card shown is in the middle
                    else
                    {
                        deckButtons[col].setText(card);
                    }
                }

            }
            catch(IOException e)
            {
                serverResponse.setText(stringData);
                e.printStackTrace();
            }
        }
    }

    private class PlayerCardActionListener implements ActionListener
    {
        int row;
        int col;
        int deckChoice;

        public PlayerCardActionListener(boolean cardChosenFromHand, int buttonRow, int buttonCol)
        {
            row = buttonRow;
            col = buttonCol;
            deckChoice = (cardChosenFromHand ? 0 : 1);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            //id, which deck clicked, row of deck clicked, col of deck clicked
            //deck 0 = draw pile, deck 1 = player deck
            String stringData = playerID+","+deckChoice+","+row+","+col;
            sendPacket(stringData);
        }
    }

    public void sendPacket(String stringData)
    {
        try
        {
            byte[] data = stringData.getBytes();

            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 5000);
            socket.send(packet);
        }
        catch(UnknownHostException e)
        {
            serverResponse.setText(e.getMessage());
            e.printStackTrace();
        }
        catch(IOException e)
        {
            serverResponse.setText(e.getMessage());
            e.printStackTrace();
        }
    }
}
