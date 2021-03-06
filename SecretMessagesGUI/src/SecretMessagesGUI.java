import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SecretMessagesGUI extends JPanel {
	private JTextField txtKey;
	private JTextArea txtIn;
	private JTextArea txtOut;
	private JSlider slider;

	public String encode(String message, int k) {
		String out = "";
		char key = (char) k;

		for (int x = 0; x < message.length(); x++) {

			char in = message.charAt(x);

			if (in >= 'A' && in <= 'Z') {

				in += key;
				if (in > 'Z')
					in -= 26;
				if (in < 'A')
					in += 26;
			}
			if (in >= 'a' && in <= 'z') {

				in += key;
				if (in > 'z')
					in -= 26;
				if (in < 'a')
					in += 26;
			}
			if (in >= '0' && in <= '9') {

				in += (k % 10);
				if (in > '9')
					in -= 10;
				if (in < '0')
					in += 10;
			}

			out += in;

		}

		return out;
	}

	public SecretMessagesGUI() {
		setLayout(null);

		txtIn = new JTextArea();
		txtIn.setBounds(10, 11, 430, 118);
		add(txtIn);

		txtOut = new JTextArea();
		txtOut.setBounds(10, 171, 430, 118);
		add(txtOut);

		JLabel lblNewLabel = new JLabel("KEY:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(161, 140, 46, 20);
		add(lblNewLabel);

		txtKey = new JTextField();
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setText("0");
		txtKey.setBounds(217, 140, 52, 23);
		add(txtKey);
		txtKey.setColumns(10);

		JButton btnNewButton = new JButton("ENCODE/DECODE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider.setValue(Integer.parseInt(txtKey.getText()));
				String message = txtIn.getText();
				int key = Integer.parseInt(txtKey.getText());
				String output = encode(message, key);
				txtOut.setText(output);

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(276, 140, 164, 23);
		add(btnNewButton);
		setPreferredSize(new Dimension(450, 300));

		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txtKey.setText("" + slider.getValue());

				String message = txtIn.getText();
				int key = Integer.parseInt(txtKey.getText());
				String output = encode(message, key);
				txtOut.setText(output);
			}
		});
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(13);
		slider.setMinorTickSpacing(1);
		slider.setValue(0);
		slider.setMinimum(-13);
		slider.setMaximum(13);
		slider.setBounds(10, 127, 156, 45);
		add(slider);
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Abhijeet's Secret Message App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new SecretMessagesGUI());
		frame.pack();
		frame.setVisible(true);

	}
}
