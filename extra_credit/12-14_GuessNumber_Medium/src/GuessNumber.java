import javax.swing.*;
import java.awt.*;

public class GuessNumber extends JPanel
{
    private static final String GUESS_FIELD_DEFAULT_TEXT = "Enter your guess here";
    private static final String GUESS_LABEL_DEFAULT_TEXT = "";


    private int number;
    private Integer previousGuess = null;

    private JLabel topLabel;
    private JTextField guessField;
    private JLabel guessLabel;
    private JButton guessButton;
    private JButton newGameButton;

    private boolean isRunning = false;

    private GuessWorker worker;

    public int getGuess()
    {
        return Integer.parseInt(guessField.getText());
    }

    public GuessNumber()
    {
        super(new GridLayout(5,1));

        number = (int) (Math.random()*1000+1);

        System.out.println(number);

        Dimension buttonDimension = new Dimension(300, 20);

        //Setup the frame
        setSize(new Dimension(350,350));

        topLabel = new JLabel("I have a number between 1 and 1000. Can you guess my number? " +
                "\nPlease enter your first guess.");
        topLabel.setSize(new Dimension(300, 20));
        topLabel.setLocation(0,0);

        //Setup where user will enter guess
        guessField = new JTextField(GUESS_FIELD_DEFAULT_TEXT);
        guessField.setSize(new Dimension(300, 50));
        guessField.setLocation(25, 5);

        //Setup the Guess! button
        guessButton = new JButton("Guess!");
        guessButton.setSize(buttonDimension);
        guessButton.setLocation(25,60);

        //Setup the guess label
        guessLabel = new JLabel(GUESS_LABEL_DEFAULT_TEXT);
        guessLabel.setSize(new Dimension(300, 20));
        guessLabel.setLocation(25, 80);

        //Setup the new game button
        newGameButton = new JButton("New Game");
        newGameButton.setSize(buttonDimension);
        newGameButton.setLocation(25, 100);
        newGameButton.setVisible(false);
        newGameButton.setEnabled(false);

        //Setup the listeners
        worker = new GuessWorker(this);
        guessField.addActionListener(e -> worker.processGuess());
        guessButton.addActionListener(e -> worker.processGuess());
        newGameButton.addActionListener(e -> worker.newGame());
    }

    public void newGame()
    {
        number = (int) (Math.random()*1000+1);
        previousGuess = null;

        guessLabel.setText(GUESS_LABEL_DEFAULT_TEXT);
        guessField.setText(GUESS_FIELD_DEFAULT_TEXT);
        setBackground(Color.WHITE);

        System.out.println(number);
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
            add(newGameButton);

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

    public synchronized void toggleMode()
    {
        newGameButton.setEnabled(!newGameButton.isEnabled());
        guessButton.setEnabled(!guessButton.isEnabled());
        guessField.setEnabled(!guessField.isEnabled());

        newGameButton.setVisible(!newGameButton.isVisible());
    }


}
