import javax.swing.*;

public class GuessNumberDriver
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Guess Number");


        GuessNumber game = new GuessNumber();
        game.run();

        frame.add(game);
        frame.pack();
        frame.setVisible(true);
    }
}
