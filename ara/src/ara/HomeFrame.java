package ara;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.BorderLayout;
import java.awt.FlowLayout; 

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton; 
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import static java.lang.System.out; 


public class HomeFrame extends JFrame
{
	// Variable Declaration 
	private JTextField usernameField; 
	private JTextField passwordField; 
	private JButton clearButton; 
	private JButton okButton; 
	private HomeListener buttonListener; 
	private JLabel label; 
	private JLabel michLabel; 
	
	public HomeFrame (String inTitle)
	{
		super(inTitle); 
		
		// Panel Delcration
		JPanel headerPanel; 
		JPanel namePanel; 
		JPanel buttonPanel; 
		
		// Header Panel
		headerPanel = new JPanel (new FlowLayout()); 
		headerPanel.add (new JLabel ("Please enter username and password to login!", SwingConstants.CENTER));
		
		// Add mich picture 
		michLabel = new JLabel((new ImageIcon("/../img/mich/jpg")));
		headerPanel.add(michLabel);
		
		// Name Field 
		usernameField = new JTextField(20); 
		passwordField = new JTextField(20); 
		namePanel = new JPanel( new FlowLayout()); 
		namePanel.add (new JLabel("Username: ", SwingConstants.RIGHT)); 
		namePanel.add (usernameField); 
		namePanel.add(new JLabel("Password: ", SwingConstants.RIGHT)); 
		namePanel.add (passwordField); 
		
		// Button Fields
		clearButton = new JButton("Clear"); 
		okButton = new JButton ("OK"); 
		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(clearButton);
		buttonPanel.add (okButton); 
		
		// GUI Layout
		setLayout(new BorderLayout()); 
		add(headerPanel, BorderLayout.NORTH);
		add(namePanel, BorderLayout.CENTER); 
		add (buttonPanel, BorderLayout.SOUTH); 
		
		// Hook up button listeners
		buttonListener = new HomeListener(); 
		clearButton.addActionListener(buttonListener); 
		okButton.addActionListener(buttonListener); 
	}
	
	public class HomeListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			// If OK Button was clicked 
			if (e.getSource() == okButton){
				// Open Song Select Frame, passing in username 
				SongSelectFrame songFrame = new SongSelectFrame(usernameField.getText());
				songFrame.setSize(450, 500); 
				songFrame.setVisible(true); 
				
				out.printf("Hello %s, Welcome to Beats with Ara! \n", 
						   usernameField.getText()); 
			}	
		}
	} // end of HomeListener	
} // end of HomeFrame 





































