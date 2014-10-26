package ara;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static java.lang.System.out;


public class SongSelectFrame extends JFrame
{
  // Variable Declaration
  private JTextField searchField; // need for later to possibly search over a
                                  // song?
  private SongSelectListener buttonListener;
  private JButton okButton;
  private JButton backButton;

  // Temp Buttons for now (Ruifu - if possible, can you replace with database of
  // songs in future)
  private JButton songOneButton;
  private JButton songTwoButton;
  private JButton songThreeButton;

  public SongSelectFrame(String inTitle)
  {
    super("Select A Song");

    // Panels for layout
    JPanel headerPanel;
    JPanel songPanel;
    JPanel buttonPanel;
    JPanel searchPanel; // for future feature

    // Header Panel
    headerPanel = new JPanel(new FlowLayout());
    String welcomeMessage = "Welcome " + inTitle + ", Please select a song!";
    headerPanel.add(new JLabel(welcomeMessage, SwingConstants.CENTER));

    // Adding Buttons
    songOneButton = new JButton("Select Song One");
    songTwoButton = new JButton("Select Song Two");
    songThreeButton = new JButton("Select Song Thee");

    // Name Field
    // Ruifu - Need to pull songs from database and loop over all songs
    // available (if possible)
    songPanel = new JPanel(new FlowLayout());
    songPanel.add(new JLabel("Song One, Level: HARD", SwingConstants.RIGHT));
    songPanel.add(songOneButton);
    songPanel.add(new JLabel(" Song Two, Level: MEDIUM   ",
        SwingConstants.RIGHT));
    songPanel.add(songTwoButton);
    songPanel.add(new JLabel(" Song Three, Level: EASY", SwingConstants.RIGHT));
    songPanel.add(songThreeButton);

    // Search Bar ** need to format to add user **
    // searchPanel = new JPanel (new FlowLayout());
    // searchField = new JTextField(20);
    // searchPanel.add (new JLabel("Search for a song: "),
    // SwingConstants.CENTER);
    // songPanel.add(searchPanel);

    // Button Fields
    backButton = new JButton("Back");
    okButton = new JButton("OK");
    buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(backButton);
    buttonPanel.add(okButton);

    // GUI Layout
    setLayout(new BorderLayout());
    add(headerPanel, BorderLayout.NORTH);
    add(songPanel, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Button Listeners
    buttonListener = new SongSelectListener();
    backButton.addActionListener(buttonListener);
    okButton.addActionListener(buttonListener);
    songOneButton.addActionListener(buttonListener);
    songTwoButton.addActionListener(buttonListener);
    songThreeButton.addActionListener(buttonListener);
  }

  public class SongSelectListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      if( e.getSource() == okButton )
      { // okButton clicked
        out.println("Song selected via ok");
      }
      else if( e.getSource() == backButton )
      { // backButton clicked
        out.println("Reset Selected");
        // Show HomeFrame
        HomeFrame home = new HomeFrame("Home");
        home.setVisible(true); // show frame
        home.setSize(300, 350);
      }
      else if( e.getSource() == songOneButton )
      { // user selected songOne
        out.println("Song One was selected!");
        gameFrame newGame = new gameFrame("Song One");
        newGame.setVisible(true);
        newGame.setSize(1000, 1000);
      }
      else if( e.getSource() == songTwoButton )
      { // user selected songTwo
        out.println("Song two was selected!");
        gameFrame newGame = new gameFrame("Song Two");
        newGame.setVisible(true);
        newGame.setSize(1000, 1000);
      }
      else if( e.getSource() == songThreeButton )
      { // user selected songThree
        out.println("Song Three was selected!");
        gameFrame newGame = new gameFrame("Song Three");
        newGame.setVisible(true);
        newGame.setSize(1000, 1000);
      }
    }
  } // end of songSelectListener
} // end of songSelectFrame


