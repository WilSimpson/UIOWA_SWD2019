import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
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
    private String[] possibleWords = {"HORSE", "APPLE"};

    private int hangmanIndex = 0;

    private Shape[] hangmanShapes = new Shape[10];

    private String word;
    private boolean[] showChar;

    @FXML
    private MenuItem nameGameMenuItem;

    @FXML
    private Pane hangmanPanel;

    @FXML
    private Line hangman3;

    @FXML
    private Line hangman2;

    @FXML
    private Line hangman1;

    @FXML
    private Line hangman4;

    @FXML
    private Line hangman6;

    @FXML
    private Circle hangman5;

    @FXML
    private Line hangman7;

    @FXML
    private Line hangman8;

    @FXML
    private Line hangman9;

    @FXML
    private Line hangman10;

    @FXML
    private Label helpLabel;

    @FXML
    private TextField failedGuessesTextfield;

    @FXML
    private TextField guessTextField;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField wordTextField;

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

    @FXML
    public void newGameClicked(ActionEvent event)
    {
        hangmanIndex = 0;

        //Reset the hangman
        for(int i=0; i<hangmanShapes.length; i++)
        {
            hangmanShapes[i].setVisible(false);
        }
    }

    @FXML
    public void onConfirmClicked(ActionEvent event)
    {
        String guess = guessTextField.getText();
        if(guess == null)
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
            }
        }
    }

    public void randomlyChooseWord()
    {
        Random random = new Random();
        int randomIndex = random.nextInt(possibleWords.length);
        System.out.println(randomIndex);
        word = possibleWords[randomIndex];
        showChar = new boolean[word.length()];
    }

    public void updateHangman()
    {
        hangmanShapes[hangmanIndex].setVisible(true);
    }

    public boolean isWordFullyGuessed()
    {
        for(int i=0; i<showChar.length; i++)
        {
            if(!showChar[i]) return false;
        }

        return true;
    }

    public boolean isHangmanDead()
    {
        return hangmanIndex >= word.length()-1;
    }

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
