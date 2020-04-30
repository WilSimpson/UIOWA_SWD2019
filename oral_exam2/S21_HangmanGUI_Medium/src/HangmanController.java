import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.util.Random;

public class HangmanController
{
    /**
     * Array of all possible words to guess
     */
    private static final String[] POSSIBLE_WORDS = {"HORSE", "APPLE"};

    /**
     * Next hangman index to show
     */
    private int hangmanIndex = 0;

    /**
     * Array containing all of the pieces in ascending order from index 0 of that hangman
     */
    private Shape[] hangmanShapes = new Shape[10];

    /**
     * Current word that is being guessed
     */
    private String word;

    /**
     * Whether the character at this index should be shown
     */
    private boolean[] showChar;

    /**
     * "File -> New Game" menu item
     */
    @FXML
    private MenuItem nameGameMenuItem;

    /**
     * Hangman panel containing all of the hangman shapes
     */
    @FXML
    private Pane hangmanPanel;

    /**
     * First piece of the hangman drawing
     */
    @FXML
    private Line hangman1;

    /**
     * Second piece of the hangman drawing
     */
    @FXML
    private Line hangman2;

    /**
     * Third piece of the hangman drawing
     */
    @FXML
    private Line hangman3;

    /**
     * Fourth piece of the hangman drawing
     */
    @FXML
    private Line hangman4;

    /**
     * Fifth piece of the hangman drawing
     */
    @FXML
    private Circle hangman5;

    /**
     * Sixth piece of the hangman drawing
     */
    @FXML
    private Line hangman6;

    /**
     * Seventh piece of the hangman drawing
     */
    @FXML
    private Line hangman7;

    /**
     * Eighth piece of the hangman drawing
     */
    @FXML
    private Line hangman8;

    /**
     * Ninth piece of the hangman drawing
     */
    @FXML
    private Line hangman9;

    /**
     * Last piece of the hangman drawing
     */
    @FXML
    private Line hangman10;

    /**
     * Label that will give the user helpful information about the status of the game
     */
    @FXML
    private Label helpLabel;

    /**
     * All past incorrect guesses
     */
    @FXML
    private TextField failedGuessesTextfield;

    /**
     * TextField where user will input their guess
     */
    @FXML
    private TextField guessTextField;

    /**
     * Button to submit a guess
     */
    @FXML
    private Button confirmButton;

    /**
     * Contains the current correct guesses and remaining spaces for guesses
     */
    @FXML
    private TextField wordTextField;

    /**
     * Called after the application has been fully processed. Randomly chooses a word and sets it up in the display.
     * Adds the hangman shape pieces to the main array.
     */
    @FXML
    public void initialize()
    {
        randomlyChooseWord();
        setHangmanWord();

        hangmanShapes[0] = hangman1;
        hangmanShapes[1] = hangman2;
        hangmanShapes[2] = hangman3;
        hangmanShapes[3] = hangman4;
        hangmanShapes[4] = hangman5;
        hangmanShapes[5] = hangman6;
        hangmanShapes[6] = hangman7;
        hangmanShapes[7] = hangman8;
        hangmanShapes[8] = hangman9;
        hangmanShapes[9] = hangman10;
    }

    /**
     * Called when "File -> New Game" has been clicked. Starts a new game by resetting all variables to their default
     * state.
     *
     * @param event event related to the action
     */
    @FXML
    public void newGameClicked(ActionEvent event)
    {
        hangmanIndex = 0;

        //Reset the hangman
        for(int i=0; i<hangmanShapes.length; i++)
        {
            hangmanShapes[i].setVisible(false);
        }

        failedGuessesTextfield.setText("");
        helpLabel.setText("Enter your guess!");
        randomlyChooseWord();
        setHangmanWord();
    }

    /**
     * Called when the "Guess" button is clicked or guessTextField is active and Enter is pressed. Checks if the given
     * guess in the guessTextField is correct. The results of their guess will be given to them in the helpLabel and
     * the game state will be updated. If the user enters something invalid they will also be notified via the helpLabel.
     *
     * @param  event related to the action
     */
    @FXML
    public void onConfirmClicked(ActionEvent event)
    {
        String guess = guessTextField.getText();
        if(isHangmanDead())
        {
            helpLabel.setText("GAME OVER! To start a new game go to File -> New Game");
        }
        else if(guess.equals(""))
        {
            helpLabel.setText("Error: Must enter a guess!");
        }
        else if(guess.length() > 1)
        {
            helpLabel.setText("Error: Can only enter in a single character guess!");
        }
        else
        {
            if(word.contains(guess))
            {
                for(int i=0; i<word.length(); i++)
                {
                    if(word.charAt(i) == guess.charAt(0))
                    {
                        if(showChar[i] == true)
                        {
                            helpLabel.setText("OOPS! You've already made that guess!");
                            return;
                        }
                        showChar[i] = true;
                    }
                }

                setHangmanWord();

                if(isWordFullyGuessed())
                {
                    helpLabel.setText("WOW! Great job, you won!");
                }
                else
                {
                    helpLabel.setText("NICE! That was in the word!");
                }
            }
            else if(failedGuessesTextfield.getText().contains(guess))
            {
                helpLabel.setText("OOPS! You've already made that guess!");
            }
            else
            {
                updateHangman();
                hangmanIndex++;

                if(isHangmanDead())
                {
                    helpLabel.setText("DANG! You lost, better luck next time!");
                }
                else
                {
                    helpLabel.setText("OOPS! That wasn't in the word!");
                }

                if(failedGuessesTextfield.getText().equals(""))
                {
                    failedGuessesTextfield.appendText(guess);
                }
                else
                {
                    failedGuessesTextfield.appendText(" "+guess);
                }
            }
        }

        guessTextField.clear();
    }

    /**
     * Randomly chooses a word from the POSSIBLE_WORDS array.
     */
    public void randomlyChooseWord()
    {
        Random random = new Random();
        int randomIndex = random.nextInt(POSSIBLE_WORDS.length);
        System.out.println(randomIndex);
        word = POSSIBLE_WORDS[randomIndex];
        showChar = new boolean[word.length()];
    }

    /**
     * Gets the shape at the current hangman index in the hangmanShapes array and shows it
     */
    public void updateHangman()
    {
        hangmanShapes[hangmanIndex].setVisible(true);
    }

    /**
     * Checks if every value in the showChar array is true.
     *
     * @return whether the user has correctly guessed all letters in the word
     */
    public boolean isWordFullyGuessed()
    {
        for(int i=0; i<showChar.length; i++)
        {
            if(!showChar[i]) return false;
        }

        return true;
    }

    /**
     * Checks if the current handmanIndex is greater than or equal to the number of hangman pieces.
     *
     * @return whether the user has shown the full hangman
     */
    public boolean isHangmanDead()
    {
        return hangmanIndex >= hangmanShapes.length;
    }

    /**
     * Uses the current word and the showChar array to represent the current state of the hangman game in the
     * wordTextField.
     */
    public void setHangmanWord()
    {
        if(word == null || word == "") return;

        String hangmanWord = "";
        for(int i=0; i<word.length(); i++)
        {
            if(showChar[i])
                hangmanWord += word.substring(i, i+1)+" ";
            else
                hangmanWord += "_ ";
        }

        wordTextField.setText(hangmanWord.substring(0, hangmanWord.length()-1));
    }
}
