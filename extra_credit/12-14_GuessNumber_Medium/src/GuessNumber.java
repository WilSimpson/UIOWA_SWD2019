import javax.swing.*;
import java.awt.*;

public class GuessNumber extends JFrame
{
    private int number;
    private Integer previousGuess = null;

    private JLabel topLabel = new JLabel("I have a number between 1 and 1000. Can you guess my number?" +
            "\nPlease enter your first guess.");
    private JTextField guessField;
    private JLabel guessLabel;
    private JButton guessButton;
    private JButton newGameButton;

    private boolean isRunning = false;

    public int getGuess()
    {
        return Integer.parseInt(guessField.getText());
    }

    public GuessNumber()
    {
        super("Guess Number");



        //Generate a random number
        newGame();



        System.out.println(number);

        //Setup the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(350,350));
        getContentPane().setBackground(Color.WHITE);

        //Setup where user will enter guess
        guessField = new JTextField("Enter your guess here");
        guessField.setSize(new Dimension(300, 50));
        guessField.setLocation(25, 5);



        //Setup the Guess! button
        guessButton = new JButton("Guess!");
        guessButton.setSize(new Dimension(300, 20));
        guessButton.setLocation(25,60);

        //Setup the guess label
        guessLabel = new JLabel("");
        guessLabel.setSize(new Dimension(300, 20));
        guessLabel.setLocation(25, 80);

        //Setup the button listener


        newGameButton = new JButton("NEW GAME");
        newGameButton.setSize(new Dimension(300, 20));
        newGameButton.setLocation(25, 100);
        newGameButton.setVisible(false);



        GuessWorker worker = new GuessWorker(this);
        guessField.addActionListener(e -> worker.processGuess());
        guessButton.addActionListener(e -> worker.processGuess());
        newGameButton.addActionListener(e -> worker.processGuess());
    }

    public void newGame()
    {
        number = (int) (Math.random()*1000+1);
    }

    public boolean run()
    {
        if(!isRunning)
        {
            isRunning = true;

            add(topLabel);
            add(guessField);
            add(guessButton);
            add(guessLabel);

            setVisible(true);

            return true;
        }

        return false;
    }

    public void setGuessLabel(String text)
    {
        guessLabel.setText(text);
    }

    /**
     * Checks whether the given guess is higher or equal to the number
     *
     * @param guess guess that was made
     * @return whether the guess was higher or equal to the number
     */
    public boolean isGuessHigh(int guess)
    {
        return guess >= number;
    }

    /**
     * Makes a guess given a number. Sets the previous guess to the given guess and returns true if the guess is closer,
     * false if the guess is farther and null if this is the first guess.
     *
     * @param guess new guess that was made
     * @return whether the guess was closer or farther
     */
    public GuessResult makeGuess(int guess)
    {
        GuessResult result = isCloser(guess);
        previousGuess = guess;

        return result;
    }

    /**
     * Checks whether the current guess is closer than the previous guess. If the guess is the same distance then it
     * will return true
     *
     * @param guess new guess
     * @return whether the new guess is closer than the previous
     */
    public GuessResult isCloser(int guess)
    {
        if(guess == number) return GuessResult.CORRECT;

        if(previousGuess == null) return GuessResult.FIRST;

        return (Math.abs(guess-number)) <= (Math.abs(previousGuess-number)) ? GuessResult.CLOSER : GuessResult.FURTHER;
    }

    public void toggleMode()
    {
        newGameButton.setEnabled(!newGameButton.isEnabled());
        guessButton.setEnabled(!newGameButton.isEnabled());
        guessField.setEnabled(!newGameButton.isEnabled());
    }

    public static void main(String[] args)
    {
        GuessNumber game = new GuessNumber();
        game.run();
    }
}
