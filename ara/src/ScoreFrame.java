import static java.lang.System.out;

import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ara.Database;

public class ScoreFrame extends JFrame {
  
  // UI Buttons and Labels
  JLabel scoreLabel = new JLabel ("Your score is  "); 
  JLabel keyLabel = new JLabel("Game Stats"); 
  JButton playAgainBtn = new JButton ("Play Again"); 
  JButton quitBtn = new JButton ("Quit"); 
  
  // Game Score
  private int score; 
  private String songName;
  private final JLabel high1 = new JLabel("");
  private final JLabel high2 = new JLabel("");
  private final JLabel high3 = new JLabel("");
  
  

  // Score Frame 
  ScoreFrame(int sco, String sn) {
    // Game Background
    
    setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/olivertang/Desktop/UMICH/EECS_481/project/background.jpg"));
    getContentPane().setBackground(new Color(230, 230, 250)); 
    
    // JFrame Settings
    setSize(1920,1080);
    //setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    
    // Score
    //int score = Game.score; // Get game score from Game.score buton 
    score = sco;
    songName = sn;
    String scoreMessage = BeatswithAra.username + "'s final score is:\n "; 
    scoreMessage = scoreMessage + score;
    
    getContentPane().setLayout(null);
    keyLabel.setForeground(Color.WHITE);
    keyLabel.setFont(new Font("Helvetica", Font.BOLD, 32));
    keyLabel.setBounds(864, 61, 213, 90);
    getContentPane().add (keyLabel); 
    quitBtn.setBounds(910, 725, 186, 69);
    quitBtn.setBackground(Color.GREEN);
    getContentPane().add (quitBtn); 
    
    // Listener for Quit Button 
    quitBtn.addActionListener(new ActionListener() {   
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("Quit the game");
                setVisible(false); 
                System.exit(0);
            }
    }); 
    
    scoreLabel.setForeground(Color.WHITE);
    scoreLabel.setFont(new Font("Helvetica", Font.BOLD, 35));
    scoreLabel.setBounds(735, 142, 548, 100);
    
    scoreLabel.setText(scoreMessage);
    getContentPane().add (scoreLabel); 
    playAgainBtn.setBounds(279, 142, 172, 69);
    playAgainBtn.setBackground(Color.RED);
    playAgainBtn.setForeground(Color.BLACK);
    getContentPane().add (playAgainBtn); 
    
    // Listener for Play Again 
    playAgainBtn.addActionListener(new ActionListener() {
       
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("Play The song again!");
                Refresh refresh = new Refresh();
                refresh.run();
                Starter.beat.run();
                setVisible(false); 
                dispose(); 
                // Goes back to the home screen 
                // Starter.beat.run();
                
            }
    });
    playAgainBtn.setVisible(false);
   
    getContentPane().add(high1);
    high2.setBounds(735, 430, 548, 100);
    
    getContentPane().add(high2);
    high3.setBounds(735, 587, 548, 100);
    
    getContentPane().add(high3);
   
    JLabel background = new JLabel("");
    background.setIcon(new ImageIcon("D:\\481 Project\\GUI\\ara\\background.jpg"));
    background.setBounds(0, 0, 1920, 1017);
    getContentPane().add(background);
    high1.setBounds(735, 284, 548, 100);
  } 
  
  public void run(){
    
    // need to get score data 
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setVisible(true); 
    Database gamedb = new Database();
    try
    {
      gamedb.connect();
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }

    gamedb.recordScore(score,BeatswithAra.nextID, songName);
    String[] scoreboard = gamedb.getHighestScore(songName);
    if(scoreboard.length > 0)
    {
      high1.setText(scoreboard[0]);
    }
    if(scoreboard.length > 1)
    {
      high1.setText(scoreboard[1]);
    }
    if(scoreboard.length > 2)
    {
      high1.setText(scoreboard[2]);
    }
    gamedb.disconnect();
  
    //keyText.setText("Hello world"); 
  }
  
  
}
