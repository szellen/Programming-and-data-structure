/* Jiajia Liang
 * jl9pg
 * extra credit project
 */

/*
 * Source: Big Java 2.9 Frame Windows and Displaying a Component in a Frame
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Draft extends JFrame {

	private static ArrayList<Integer> randomNum;
	private static final int ARRAY_SIZE = 5,  MAX = 80, MIN = 1;

	JLabel SelectionSort;
	JLabel InsertionSort;
	JLabel BubbleSort;
	JButton startButton;

	// main method
	public static void main(String[] args) {
		randomNum = new ArrayList<Integer>();
		for(int i = 0; i < ARRAY_SIZE; i++) {
			randomNum.add((int)(Math.random()*MAX+MIN));
		}
		new SortGUI();
		System.out.println(randomNum);
		modifiedSelectionSort();
	}

	/**
	 * 
	 */

	public SortGUI() {

		// make sure the program exits when the frame closes
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		this.setSize(width/2, height*5/11);

		// This will center the JFrame in the middle of the screen
		this.setLocationRelativeTo(null);

		// ----------------first panel - title of the program------------------------


		final JPanel p1= new JPanel();
		p1.setBackground(Color.WHITE);

		startButton = new JButton("Click to start the search!");
		p1.add(startButton);
		startButton.addActionListener(new StartButtonListener());


		// ----------------second panel - adding course to the list------------------
		final JPanel p2 = new JPanel();
		p2.setBackground(Color.WHITE);
		p2.setLayout(new BorderLayout());

		SelectionSort = new JLabel("Selection Sort");
		InsertionSort = new JLabel("Insertion Sort");
		BubbleSort = new JLabel("Bubble Sort");
		SelectionSort.setFont(new Font("Serif", Font.PLAIN, width / 60));
		InsertionSort.setFont(new Font("Serif", Font.PLAIN, width / 60));
		BubbleSort.setFont(new Font("Serif", Font.PLAIN, width / 60));;

		p2.add(SelectionSort,BorderLayout.NORTH);
		p2.add(InsertionSort,BorderLayout.CENTER);
		p2.add(BubbleSort,BorderLayout.SOUTH);


		// ----------------third panel ----------------------------------------------

		final JPanel p3 = new JPanel();
		p3.setBackground(Color.WHITE);
		p3.setLayout(new BorderLayout());

		RectangleComponent component1 = new RectangleComponent();
		p3.add(component1);

		// --------------------END OF THIRD PANEL------------------------------------
		// add panels to frame

		this.add(p1, BorderLayout.NORTH);
		this.add(p2, BorderLayout.WEST);
		this.add(p3,BorderLayout.CENTER);

		//this.pack();
		setVisible(true);
	}



	// add course button actionListner - update course name, credit, and GPA
	private class StartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			modifiedSelectionSort();
			modifiedInsertionSort();
			modifiedBubbleSort();
		}
	}	



	private static void modifiedSelectionSort() {
		int n = ARRAY_SIZE;	
		ArrayList<Integer> arr = randomNum;
		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n-1; i++)
		{
			// Find the minimum element in unsorted array
			int min_idx = i;
			for (int j = i+1; j < n; j++)
				if (arr.get(j) < arr.get(min_idx))
					min_idx = j;

			// Swap the found minimum element with the first
			// element
			int temp = arr.get(min_idx);
			arr.set(min_idx, arr.get(i));
			arr.set(i, temp);
			System.out.println(arr);

		}
	}

	private void modifiedInsertionSort() {
		// ??? YOUR CODE HERE
	}
	private void modifiedBubbleSort() {

	}



	public class RectangleComponent extends JComponent{
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;		
			for (int i = 0; i < randomNum.size(); i++) {
				Rectangle box = new Rectangle(30*i, 10, 30, randomNum.get(i));
				g2.draw(box);
				box.translate(0, 145);
				g2.draw(box);
				box.translate(0, 160);
				g2.draw(box);
			}
		}
	}
}