package randomwhackycards;

/**
 * *****************************
 * 12/6/11 commit by Vic ****************************
 */
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Random;
import javax.swing.*;

public class Main implements Runnable, ActionListener, MouseListener
{

    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    public JFrame cardWindow;
    int clicks = 0;
    private int cardSize = 4;
    private int jillScore;
    private int jackScore;
    public int winningScore = 500;
    public int[][] intArray;
    public JButton[][] buttonArray;
    public Random r;
    private JButton j;
    private Icon marioPic;
    private URL iconAddress;
    private Timer timer;
    private int row;
    private int col;
    private int presentNumber;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Main());
    }

    @Override
    public void run()
    {
        buttonArray = new JButton[cardSize][cardSize];
        intArray = new int[cardSize][cardSize];
        iconAddress = getClass().getResource("MarioBrosArtwork.jpg");
        marioPic = new ImageIcon(iconAddress);
        timer = new Timer(2000, this);
        timer.setRepeats(false);
        r = new Random();
        cardWindow = new JFrame("RandomWhackyCards");//make jframe
        cardWindow.setSize(width, height);//set size
        cardWindow.setVisible(true);//set visible
        cardWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//make it able to close
        cardWindow.setLayout(new GridLayout(cardSize, cardSize));
        int tempr = r.nextInt(3);
        int tempc = r.nextInt(3);

        for (row = 0; row < cardSize; row++)
        {
            for (col = 0; col < cardSize; col++)
            {
                buttonArray[row][col] = new JButton(marioPic);
                buttonArray[row][col].setMnemonic(r.nextInt(100));
                cardWindow.add(buttonArray[row][col]);
                buttonArray[row][col].addMouseListener(this);
                buttonArray[row][col].setFont(new Font("Bank Gothic", Font.BOLD, 90));
            }
        }
        for (row = 0; row < cardSize; row++)
        {
            for (col = 0; col < cardSize; col++)
            {
                if (tempr > tempc)
                {
                    buttonArray[tempr][col].setMnemonic(r.nextInt(201));
                }
                if (tempc > tempr)
                {
                    buttonArray[row][tempc].setMnemonic(r.nextInt(201));
                }
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
        if (clicks == 16 && jillScore == jackScore)
        {
            JOptionPane.showMessageDialog(null, "Draw no one won.  Score was " + (jillScore));
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
    }

    @Override
    public void mouseClicked(MouseEvent me1)
    {
    }

    @Override
    public void mousePressed(MouseEvent me2)
    {
        JButton temp = (JButton) me2.getSource();
        try
        {
            presentNumber = Integer.parseInt(temp.getText());
        } catch (NumberFormatException e2)//no cheating
        {
            temp.setIcon(null);
            temp.setFont(new Font("Ariel", Font.BOLD, 99));
            temp.setText("" + temp.getMnemonic());
            updateScore(temp.getMnemonic());

        }
    }

    @Override
    public void mouseReleased(MouseEvent me3)
    {
    }

    @Override
    public void mouseEntered(MouseEvent me4)
    {
    }

    @Override
    public void mouseExited(MouseEvent me5)
    {
    }
}
