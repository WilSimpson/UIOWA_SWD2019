import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

public class GuessNumber
{
    private int number;
    private Integer previousGuess = null;

    public GuessNumber()
    {
        number = (int) (Math.random()*1000+1);
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
    public Boolean makeGuess(int guess)
    {
        if(previousGuess == null) return null;

        boolean result = isCloser(guess);

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
    private boolean isCloser(int guess)
    {
        return (Math.abs(guess-number)) <= (Math.abs(previousGuess-number));
    }

    public static void main(String[] args)
    {
        GuessNumber game = new GuessNumber();

        JFrame frame = new JFrame("Guess Number");
        frame.setSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.WHITE);

        JLabel topLabel = new JLabel("I have a number between 1 and 1000. Can you guess my number?" +
                "\nPlease enter your first guess.");
        JLabel lowOrHighLabel = new JLabel("");

        JTextField guessField = new JTextField("Your guess");
        guessField.setSize(new Dimension(300, 50));
        guessField.setLocation(0, 0);

        JButton guessButton = new JButton("Guess!");
        guessButton.setSize(new Dimension(300, 20));
        guessButton.setLocation(0,50);

        guessButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int guess = Integer.parseInt(guessField.getText());

                Boolean result = game.makeGuess(guess);


                //Update the background
                if(result == null)
                {
                    //This is the first guess
                    frame.setBackground(Color.YELLOW);
                }
                else if(result)
                {
                    //The guess was closer
                    frame.setBackground(Color.RED);
                }
                else
                {
                    //The guess was farther
                    frame.setBackground(Color.BLUE);
                }

                //Update the low or high button
                if(game.isGuessHigh(guess))
                {
                    lowOrHighLabel.setText("Too High");
                }
                else
                {
                    lowOrHighLabel.setText("Too Low");
                }
            }
        });

        frame.add(topLabel);
        frame.add(guessField);
        frame.add(guessButton);
        frame.add(lowOrHighLabel);

        //frame.pack();
        frame.setVisible(true);
    }
}
