package randomwhackycards;

/*******************************
 * 12/6/11 commit by Vic
 *******************************/
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main implements Runnable
{

    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    public JFrame cardWindow;
    int clicks = 0;
    private int cardSize = 4;
    private int jillScore;
    private int jackScore;
    public WhackyButton jack;
    public int winningScore = 400;
    public int[][] intArray;
    public JButton[][] buttonArray;
    public Random r;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Main());
    }

    @Override
    public void run()
    {
        buttonArray = new JButton[4][4];
        intArray = new int[4][4];
        r = new Random();
        cardWindow = new JFrame("RandomWhackyCards");//make jframe
        cardWindow.setSize(width, height);//set size
        cardWindow.setVisible(true);//set visible
        cardWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//make it able to close
        cardWindow.setLayout(new GridLayout(cardSize, cardSize));
        jack = new WhackyButton(this);
        jack.setR(r);
        jack.setIntArray(intArray);
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                intArray[i][j] = r.nextInt(100); 
            }
        }
    }

    public void updateScore(int n)
    {
        clicks++;
        if (clicks % 2 == 1)
        {
            jillScore += n;
            cardWindow.setTitle("jill = " + jillScore + "  jack = " + jackScore);
        }
        if (clicks % 2 == 0)
        {
            jackScore += n;
            cardWindow.setTitle("jill = " + jillScore + "  jack = " + jackScore);
        }
        if (jackScore > winningScore)
        {
            JOptionPane.showMessageDialog(null, "Jack Wins!!" + "jack got " + jackScore + "  ,jill only got " + jillScore);
            System.exit(0);
        }
        if (jillScore > winningScore)
        {
            JOptionPane.showMessageDialog(null, "Jill Wins!!" + " jill got " + jillScore + "  ,jack only got " + jackScore);
            System.exit(0);
        }
        if (clicks == 16 && jackScore > jillScore)
        {
            JOptionPane.showMessageDialog(null, "Draw no one won. Jack was winning by " + (jackScore - jillScore));
            System.exit(0);
        }
        if (clicks == 16 && jillScore > jackScore)
        {
            JOptionPane.showMessageDialog(null, "Draw no one won. Jill was winning by " + (jillScore - jackScore));
            System.exit(0);
        }
    }
}
