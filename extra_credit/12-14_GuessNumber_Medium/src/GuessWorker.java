import javax.swing.*;
import java.awt.*;

public class GuessWorker<T, V> extends SwingWorker<T, V>
{
    private GuessNumber game;

    public GuessWorker(GuessNumber game)
    {
        this.game = game;
    }

    public void processGuess()
    {
        doInBackground();
    }

    @Override
    protected T doInBackground()
    {
        System.out.println("Starting to run in background!");

        try
        {
            int guess = game.getGuess();

            //If the guess is invalid throw an exception
            if (guess < 1 || guess > 1000)
            {
                throw new NumberFormatException();
            }

            GuessResult result = game.makeGuess(guess);

            if (result == GuessResult.CORRECT)
            {
                game.toggleMode();
                game.setGuessLabel("CORRECT");
                game.getContentPane().setBackground(Color.GREEN);
            }
            else
            {
                game.setGuessLabel(game.isGuessHigh(guess) ? "Too High!" : "Too Low");

                if (result == GuessResult.CLOSER)
                {
                    game.getContentPane().setBackground(Color.BLUE);
                }
                else if(result == GuessResult.FURTHER)
                {
                    game.getContentPane().setBackground(Color.RED);
                }
            }

            System.out.println(result);
        }
        catch (NumberFormatException ex)
        {
            game.setGuessLabel("Integer inclusively between 1 and 1000");
        }

        System.out.println("No longer running in background!");
        return null;
    }
}
