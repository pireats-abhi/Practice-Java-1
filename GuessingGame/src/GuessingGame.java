import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;

public class GuessingGame extends JFrame {
	private JTextField txtGuess;	//text field for the user's guess
	private JLabel lblOutput;		//label for too high or too low output
	private int theNumber;
	int count;
	
	public void checkGuess() {
		
		count++;
		String guessTxt = txtGuess.getText(); 
		String message = "";
		
		try {
			
		
			int guess = Integer.parseInt(guessTxt);
			
			
			if (guess > theNumber)
			{
				if (count == 7) {
					newGame();
					message = "You tried 7 times, you lose the the game. Let's play again!";
					lblOutput.setText(message);
				}
				else {
					message = guess + " is too high. You tried " + count + " times. Try again!";
					lblOutput.setText(message);
				}
			}
			else if (guess < theNumber)
			{
				if (count == 7) {
					newGame();
					message = "You tried 7 times, you lose the the game. Let's play again!";
					lblOutput.setText(message);
				}
				else {
					message = guess + " is too low. You tried " + count + " times. Try again!";
					lblOutput.setText(message);
				}
			}
			else
			{
				message = guess + " is right. You win! You finished it only in " + count + " moves. Let's play again.";
				lblOutput.setText(message);
				newGame();
			}
		}
		catch (Exception e) {
			lblOutput.setText("Enter a whole number between 1 to 100.");
		}
		finally {
			txtGuess.requestFocus();
			txtGuess.selectAll();
		}
		
	}
	
	public void newGame() {
		
		theNumber = (int)(Math.random()*100 + 1);
		count = 0;
		
	}
	
	public GuessingGame() {
		setResizable(false);
		setTitle("HiLo Guessing Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.CYAN);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Abhijeet's HiLo Guessing Game");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 32, 474, 29);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel);
		
		JButton btnGuess = new JButton("Guess!");
		btnGuess.setForeground(Color.WHITE);
		btnGuess.setBackground(Color.BLACK);
		btnGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkGuess();
			}
		});
		btnGuess.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuess.setBounds(195, 161, 104, 23);
		getContentPane().add(btnGuess);
		
		lblOutput = new JLabel("Enter a number above. You have 7 tries.");
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutput.setBounds(0, 216, 494, 20);
		getContentPane().add(lblOutput);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(10, 93, 474, 36);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Guess a number between 1 to 100:");
		lblNewLabel_1.setBounds(106, 11, 207, 17);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtGuess = new JTextField();
		txtGuess.setForeground(Color.WHITE);
		txtGuess.setBackground(Color.BLACK);
		txtGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkGuess();
			}
		});
		txtGuess.setHorizontalAlignment(SwingConstants.CENTER);
		txtGuess.setBounds(311, 9, 59, 20);
		panel.add(txtGuess);
		txtGuess.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtGuess.setColumns(10);
	}

	public static void main(String[] args) {
		
		GuessingGame theGame = new GuessingGame();
		theGame.newGame();
		theGame.setSize(new Dimension(500, 300));
		theGame.setVisible(true);
		

	}
}
