import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

/**
 * CLIENT PACKETS
 * Starts with c = They want to connect
 * Stars with # = their player ID
 *      followed with 0 = chose from their cards
 *      followed with 1 = chose from the middle cards
 *          followed by # = row
 *              fol by # = col
 *
 * SERVER RESPONSE
 * Starts with e = Error
 *      followed by string = error message
 * Starts with ch = Choose starting cards
 * Starts with # = playerID who made move
 *      followed with 0 = card from hand
 *      followed with 1 = card from middle
 *          followed by # = row
 *          followed by # = col
 *          followed by string = card
 */
public class Server extends JFrame {
    private JTextArea displayArea; // displays packets received
    private DatagramSocket socket; // socket to connect to client

    private GameManager gameManager = new GameManager(2);
    InetAddress[] addresses = new InetAddress[2];
    int[] ports = new int[2];

    // set up GUI and DatagramSocket
    public Server() {
        super("Server");

        displayArea = new JTextArea(); // create displayArea
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        setSize(400, 300); // set size of window
        setVisible(true); // show window

        try // create DatagramSocket for sending and receiving packets
        {
            socket = new DatagramSocket(5000);
        } catch (SocketException socketException) {
            socketException.printStackTrace();
            System.exit(1);
        }
    }

    // wait for packets to arrive, display data and echo packet to client
    public void waitForPackets() {
        while (true) {
            try // receive packet, display contents, return copy to client
            {
                byte[] data = new byte[100]; // set up packet
                String responseString = "";
                DatagramPacket receivePacket =
                        new DatagramPacket(data, data.length);

                socket.receive(receivePacket); // wait to receive packet

                // display information from received packet
                displayMessage("\nPacket received:" +
                        "\nFrom host: " + receivePacket.getAddress() +
                        "\nHost port: " + receivePacket.getPort() +
                        "\nLength: " + receivePacket.getLength() +
                        "\nContaining:\n\t" + new String(receivePacket.getData(),
                        0, receivePacket.getLength()));

                String[] packetDataStrings = new String(receivePacket.getData(),
                        0, receivePacket.getLength()).split(",");

                System.out.println("-------------------");
                System.out.println(gameManager.getGameState());
                System.out.println("-------------------\n");

                try
                {

                    int playerID;

                    if(packetDataStrings[0].equals("c"))
                    {
                        Player newPlayer = new Player();
                        gameManager.addPlayer(newPlayer);
                        playerID = newPlayer.getID();

                        //Assign these values when a client connects so we can use them in the future
                        addresses[playerID] = receivePacket.getAddress();
                        ports[playerID] = receivePacket.getPort();

                        responseString = "ca,"+playerID;
                        sendPacketToClient(responseString.getBytes(), playerID);

                        //Game can now start since enough players have joined the game
                        if(gameManager.getNumberConnectedPlayers() == gameManager.getMaxNumberPlayers())
                        {
                            //Initialize the game
                            gameManager.initializeGame();
                            gameManager.getGameState().setCurrentID(playerID);

                            //Send packet to tell player 1 to choose two cards
                            responseString = "ch,"+playerID;
                            sendPacketToClient(responseString.getBytes(), playerID);
                        }
                    }
                    else
                    {
                        //Client sent packet that they have chosen a card
                        playerID = Integer.parseInt(packetDataStrings[0]);
                        //Check if it is the correct gamestate to do so
                        if(gameManager.getGameState() == GameManager.GameState.PLAYER_CHOOSE_SHOW_CARDS)
                        {
                            //Check if it is the client's turn
                            if(gameManager.getGameState().getCurrentID() == playerID)
                            {
                                int row = Integer.parseInt(packetDataStrings[2]);
                                int col = Integer.parseInt(packetDataStrings[3]);
                                Card cardShown = gameManager.playerChoseShowCard(playerID, row, col);

                                //Send response telling them what the card was
                                responseString = "sc,"+playerID+","+row+","+col+","+cardShown.toString();
                                sendPacketToClient(responseString.getBytes(), playerID);

                                //If the gamestate didn't change due to this then a player needs to keep choosing cards
                                if(gameManager.getGameState() == GameManager.GameState.PLAYER_CHOOSE_SHOW_CARDS)
                                {

                                    int currentID = gameManager.getGameState().getCurrentID();

                                    //Check if the player choosing cards changed, if it did let them know they need
                                    //to choose cards
                                    if(currentID != playerID)
                                    {
                                        responseString = "ch,"+currentID;
                                        sendPacketToClient(responseString.getBytes(), playerID);
                                    }
                                    //Tell the player to choose another card
                                    else
                                    {
                                        System.out.println("PLAYER CHOSE CARD");
                                        responseString = "ch,"+playerID;
                                        sendPacketToClient(responseString.getBytes(), playerID);
                                    }
                                }
                                //Else the game needs to move to the next state
                                else
                                {

                                }
                            }
                            else
                            {

                            }
                        }
                    }

                }
                catch(Exception e)
                {
                    responseString = "E,-1";
                    DatagramPacket responsePacket = new DatagramPacket(responseString.getBytes(), responseString.getBytes().length, receivePacket.getAddress(), receivePacket.getPort());
                    sendPacketToClient(responsePacket);
                }





            } catch (IOException ioException) {
                displayMessage(ioException + "\n");
                ioException.printStackTrace();
            }
        }
    }

    // echo packet to client
    private void sendPacketToClient(byte[] data, int playerID) throws IOException
    {
        // create packet to send
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, addresses[playerID], ports[playerID]);
        sendPacketToClient(sendPacket);
    }

    // echo packet to client
    private void sendPacketToClient(DatagramPacket receivePacket)
            throws IOException {

        // create packet to send
        DatagramPacket sendPacket = new DatagramPacket(
                receivePacket.getData(), receivePacket.getLength(),
                receivePacket.getAddress(), receivePacket.getPort());

        socket.send(sendPacket); // send packet to client
        displayMessage("\n\nResponse packet sent\n");
    }

    // manipulates displayArea in the event-dispatch thread
    private void displayMessage(final String messageToDisplay) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() // updates displayArea
                    {
                        displayArea.append(messageToDisplay); // display message
                    }
                }
        );
    }
}