package ara;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ara.HomeFrame.HomeListener;
import ara.SongSelectFrame.SongSelectListener;
import static java.lang.System.out;


public class gameFrame extends JFrame
{
  // Variable Declaration
  private JLabel headerLabel;
  private JLabel ScoreLabel;
  private JButton pauseButton;
  private JButton resetButton;
  private gameListener buttonListener;

  public gameFrame(String inTitle)
  {
    // Layout Panels
    JPanel headerPanel;
    JPanel gameplayPanel;
    JPanel buttonPanel;

    // Header
    String headerMessage = "Current Song: " + inTitle;
    headerPanel = new JPanel(new FlowLayout());
    headerPanel.add(new JLabel(headerMessage, SwingConstants.CENTER));

    // Main gameplay content
    gameplayPanel = new JPanel(new FlowLayout());
    gameplayPanel
        .add(new JLabel("Touch corresponding buttons on the knit wall!",
            SwingConstants.CENTER));

    // Bottom Buttons
    buttonPanel = new JPanel(new FlowLayout());
    pauseButton = new JButton("Pause");
    resetButton = new JButton("Reset");
    buttonPanel.add(pauseButton);
    buttonPanel.add(resetButton);

    // Set GUI Layout
    setLayout(new BorderLayout());
    add(headerPanel, BorderLayout.NORTH);
    add(gameplayPanel, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Button Listeners
    buttonListener = new gameListener();
    pauseButton.addActionListener(buttonListener);
    resetButton.addActionListener(buttonListener);
  }

  public class gameListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      if( e.getSource() == pauseButton )
      {
        out.println("Pause the game!");
      }
      else if( e.getSource() == resetButton )
      {
        out.println("Reset the game!");
      }
    }
  } // end of gameListener
} // end of gameFrame


