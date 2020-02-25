import javax.swing.*;
import java.awt.*;

/**
 * A JPanel that creates a game to guess a number. The minimum and maximum possible integers are set by MIN_VALUE and
 * MAX_VALUE. When the user enters a number and hits return or presses the guess button the game checks if the guess is
 * correct. If it's incorrect then it tells the user whether it was too high or too low and if the user has made a
 * previous guess then the background will change to blue if the guess was closer or red if the guess was further than
 * the previous guess. Once the user guesses correctly the background will change to green and all inputs will be
 * disabled except for a new game button that will appear. If the user presses the new game button a new number will be
 * generated, the new game button will disappear and the game will reset.
 *
 * @author Wil Simpson
 */
public class GuessNumber extends JPanel
{
    /**
     * Default value for the guess text field
     */
    private static final String GUESS_FIELD_DEFAULT_TEXT = "Enter your guess here";

    /**
     * Default value for the guess label field
     */
    private static final String GUESS_LABEL_DEFAULT_TEXT = "";

    /**
     * Minimum possible value the number to guess can be
     */
    public static final int MIN_VALUE = 1;

    /**
     * Maximum possible value the number to guess can be
     */
    public static final int MAX_VALUE = 1000;

    /**
     * Number to guess
     */
    private int number;

    /**
     * Previous guess the user made. If there is no previous guess the value should be null
     */
    private Integer previousGuess = null;

    /**
     * Top label for the GUI explaining how to play the game
     */
    private final JLabel topLabel;

    /**
     * Textfield the user will enter their guess in
     */
    private final JTextField guessField;

    /**
     * Label explaining whether the guess was invalid or if their guess was too high, too low or correct
     */
    private final JLabel guessLabel;

    /**
     * Button to press when the user wants to make a guess
     */
    private final JButton guessButton;

    /**
     * Button to press to start a new game
     */
    private final JButton newGameButton;

    /**
     * The default background color for the program
     */
    private final Color defaultBackgroundColor;

    /**
     * Gets the guess from the textfield input
     *
     * @return integer guess
     */
    public int getGuess()
    {
        return Integer.parseInt(guessField.getText());
    }

    /**
     * Completely initializes and organizes the panel
     */
    public GuessNumber()
    {
        super(new GridLayout(5, 1));

        defaultBackgroundColor = getBackground();

        //Generates a random number for the game
        number = getRandomNumber(MIN_VALUE, MAX_VALUE);

        //Logs the number to the console
        System.out.println(number);

        //Setup the top label that describes the game
        topLabel = new JLabel("I have a number between "+MIN_VALUE+" and "+MAX_VALUE+". Can you guess my number? ", JLabel.CENTER);


        //Setup where user will enter guess
        guessField = new JTextField(GUESS_FIELD_DEFAULT_TEXT, JLabel.CENTER);

        //Setup the Guess! button
        guessButton = new JButton("Guess!");

        //Setup the guess label
        guessLabel = new JLabel(GUESS_LABEL_DEFAULT_TEXT, JLabel.CENTER);

        //Setup the new game button
        newGameButton = new JButton("New Game");
        newGameButton.setVisible(false);
        newGameButton.setEnabled(false);

        //Setup the listeners
        guessField.addActionListener(e -> processGuess());
        guessButton.addActionListener(e -> processGuess());
        newGameButton.addActionListener(e -> newGame());

        //Adds all components to the frame
        add(topLabel);
        add(guessField);
        add(guessButton);
        add(guessLabel);
        add(newGameButton);
    }

    /**
     * Processes a given request by taking the number from the textfield. Handles incorrect values for textfield.
     * Changes the background color to blue if the current guess is closer than the previous guess and gives a hint
     * whether the current guess is too high or too low.
     */
    private void processGuess()
    {
        try
        {
            int guess = getGuess();

            //If the guess is invalid throw an exception
            if (guess < 1 || guess > 1000)
            {
                throw new NumberFormatException();
            }

            //Get the result of their guess
            GuessResult result = makeGuess(guess);


            if (result == GuessResult.CORRECT)
            {
                //If the guess is correct toggle the view, set the guess to correct and set the background to green
                toggleView();
                setGuessLabel("CORRECT");
                setBackground(Color.GREEN);
            }
            else
            {
                //If the guess is too low or too high then set the correct text. If the guess is closer than the
                //previous guess set the color to blue otherwise set to red
                setGuessLabel(isGuessHigh(guess) ? "Too High!" : "Too Low");

                if (result == GuessResult.CLOSER)
                {
                    setBackground(Color.BLUE);
                }
                else if(result == GuessResult.FURTHER)
                {
                    setBackground(Color.RED);
                }
            }
        }
        catch (NumberFormatException ex)
        {
            //If an error occurs while getting a number from the textfield tell the user all valid inputs
            setGuessLabel("Integer inclusively between 1 and 1000");
        }
    }

    /**
     * Generates a new number, sets the previous guess to null, resets the text and label fields, sets the background to
     * the default color and logs the new number to guess to the console.
     */
    public void newGame()
    {
        //Generates a new number and sets the previous guess to null
        number = getRandomNumber(MIN_VALUE, MAX_VALUE);
        previousGuess = null;

        //Resets the text/label fields and sets the background to the default color
        guessLabel.setText(GUESS_LABEL_DEFAULT_TEXT);
        guessField.setText(GUESS_FIELD_DEFAULT_TEXT);
        setBackground(defaultBackgroundColor);

        //Log the new number to console
        System.out.println(number);

        //Toggle the panel view so the user can enter new guesses
        toggleView();
    }

    /**
     * Gets a random int inclusively between the given min and max
     *
     * @param min minimum value that can be returned
     * @param max maximum value that can be returned
     * @return random value inclusively between min and max
     */
    public final int getRandomNumber(int min, int max)
    {
        return (int) (Math.round(Math.random()*(max-min+1))+min);
    }

    /**
     * Sets the guess label to the given string
     *
     * @param text new text to set to
     */
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

    /**
     * Switches views for playing a game and asking to play a new game. The new game button is always invisible when the
     * other components are enabled and vice-versa.
     */
    private void toggleView()
    {
        newGameButton.setEnabled(!newGameButton.isEnabled());
        guessButton.setEnabled(!guessButton.isEnabled());
        guessField.setEnabled(!guessField.isEnabled());

        newGameButton.setVisible(!newGameButton.isVisible());
    }

    /**
     * Checks if the game is over by seeing if the new game button is visible
     *
     * @return whether the game is over
     */
    public boolean isGameOver()
    {
        return newGameButton.isVisible();
    }

    /**
     * Gets the number the user is currently guessing for
     *
     * @return number user is guessing for
     */
    public int getNumber()
    {
        return number;
    }
}
