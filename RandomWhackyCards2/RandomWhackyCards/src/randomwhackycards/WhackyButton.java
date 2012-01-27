package randomwhackycards;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;

/**
 *
 * @author Erik
 */
public class WhackyButton extends JButton implements ActionListener, MouseListener
{

    private Main parent;
    private Timer timer;
    private int presentNumber;
    private Icon marioPic;
    private URL iconAddress;
    private int cardSize = 4;
    private JButton j;
    private Random r;
    private int[][] intArray;

    public WhackyButton(Main game)
    {
        parent = game;
        iconAddress = getClass().getResource("MarioBrosArtwork.jpg");
        marioPic = new ImageIcon(iconAddress);
        setFont(new Font("Bank Gothic", Font.BOLD, 90));
        timer = new Timer(2000, this);
        timer.setRepeats(false);
        setMnemonic((int) (Math.random() * 100));
        for (int row = 0; row < cardSize; row++)
        {
            for (int col = 0; col < cardSize; col++)
            {
                j = new JButton(marioPic);
                parent.cardWindow.add(j);
                j.addMouseListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        presentNumber = 0;
        timer.stop();
        setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        JButton temp = (JButton) e.getSource();
        try
        {
            presentNumber = Integer.parseInt(temp.getText());
        } catch (NumberFormatException e2)//no cheating
        {
            int randomNumber = r.nextInt(100);
            temp.setIcon(null);
            temp.setFont(new Font("Ariel", Font.BOLD, 99));
            temp.setText("" + randomNumber);
            parent.updateScore(randomNumber);
            timer.start();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }
     
    public void setR(Random r)
    {
        this.r = r;
    }

    public void setIntArray(int[][] intArray)
    {
        this.intArray = intArray;
    }
}
