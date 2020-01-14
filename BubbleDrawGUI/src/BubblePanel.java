import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class BubblePanel extends JPanel {

	private ArrayList<Bubble> bubbleList;
	private int size = 30;
	private Timer timer;
	private final int DELAY = 33; // ms of delay for 30fps
	private JTextField txtSize;
	private JTextField txtSpeed;
	private JCheckBox chkGroup;
	private JCheckBox chkPause;

	public BubblePanel() {
		bubbleList = new ArrayList<Bubble>();
		addMouseListener(new BubbleListener());
		addMouseMotionListener(new BubbleListener());
		addMouseWheelListener(new BubbleListener());

		timer = new Timer(1000 / DELAY, new BubbleListener());

		setBackground(Color.black);
		setPreferredSize(new Dimension(720, 450));

		JPanel panel = new JPanel();
		add(panel);

		JLabel lblNewLabel = new JLabel("Dot Size:");
		panel.add(lblNewLabel);

		txtSize = new JTextField();
		txtSize.setText("30");
		txtSize.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(txtSize);
		txtSize.setColumns(3);

		JLabel lblNewLabel_1 = new JLabel("Animation Speed (fps):");
		panel.add(lblNewLabel_1);

		txtSpeed = new JTextField();
		txtSpeed.setText("30");
		txtSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(txtSpeed);
		txtSpeed.setColumns(2);

		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the Bubble Size
				int newSize = Integer.parseInt(txtSize.getText());
				// get the Animation Speed
				int newSpeed = Integer.parseInt(txtSpeed.getText());
				// set the Bubble Size
				size = newSize;
				// set the Animation Speed
				timer.setDelay(1000 / newSpeed);
				repaint();
			}
		});
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bubbleList = new ArrayList<Bubble>();
				repaint();
			}
		});

		chkGroup = new JCheckBox("Group Bubbles");
		panel.add(chkGroup);

		chkPause = new JCheckBox("Pause");
		chkPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkPause.isSelected()) {
					timer.stop();
				} else
					timer.start();
			}
		});
		panel.add(chkPause);
		panel.add(btnNewButton_1);

		timer.start();

	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);

		// draw all the bubbles from bbble list
		for (Bubble bubble : bubbleList) {
			page.setColor(bubble.color);
			page.fillOval(bubble.x - bubble.size / 2, bubble.y - bubble.size / 2, bubble.size, bubble.size);
		}

		// write the numbers of bubbles on screen
		// page.setColor(Color.GREEN);
		// page.drawString("Count: " + bubbleList.size(), 5, 15);
	}

	private class BubbleListener implements MouseListener, MouseMotionListener, MouseWheelListener, ActionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// timer.stop();
			bubbleList.add(new Bubble(e.getX(), e.getY(), size));
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// timer.start();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			bubbleList.add(new Bubble(e.getX(), e.getY(), size));

			if (chkGroup.isSelected()) {
				bubbleList.get(bubbleList.size() - 1).xspeed = bubbleList.get(bubbleList.size() - 2).xspeed;
				bubbleList.get(bubbleList.size() - 1).yspeed = bubbleList.get(bubbleList.size() - 2).yspeed;
			}

			repaint();

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			size -= e.getWheelRotation();

			txtSize.setText("" + size);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			for (Bubble bubble : bubbleList) {
				bubble.update(bubble.size);
			}
			repaint();

		}

	}

	private class Bubble {
		// A bubble needs an x,y location and a size
		public int x;
		public int y;
		public int size;
		public Color color;
		public int xspeed;
		public int yspeed;
		private final int MAX_SPEED = 5;

		public Bubble(int newX, int newY, int newSize) {
			x = newX;
			y = newY;
			size = newSize;
			color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random(),
					(float) Math.random());
			xspeed = (int) (Math.random() * MAX_SPEED * 2 - MAX_SPEED);
			yspeed = (int) (Math.random() * MAX_SPEED * 2 - MAX_SPEED);

			if (xspeed == 0 && yspeed == 0) {
				xspeed = 1;
				yspeed = 1;
			}
		}

		public void update(int s) {
			x += xspeed;
			y += yspeed;

			if (x < 0 + (s / 2) || x > getWidth() - (s / 2)) {
				xspeed = -1 * xspeed;
			}
			if (y < 0 + (s / 2) || y > getHeight() - (s / 2)) {
				yspeed = -yspeed;
			}

		}
	}

}
