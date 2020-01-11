import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BubblePanel extends JPanel {

	private ArrayList<Bubble> bubbleList;

	public BubblePanel() {
		bubbleList = new ArrayList<Bubble>();

		setBackground(Color.black);
		setPreferredSize(new Dimension(600, 400));

	}

	private class Bubble {
		// A bubble needs an x,y location and a size
		public int x;
		public int y;
		public int size;

		public Bubble(int newX, int newY, int newSize) {
			x = newX;
			y = newY;
			size = newSize;
		}
	}

}
