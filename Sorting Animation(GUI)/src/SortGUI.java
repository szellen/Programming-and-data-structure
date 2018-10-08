/**
 * https://docs.oracle.com/javase/7/docs/api/javax/swing/JApplet.html
 * http://pages.cs.wisc.edu/~bahls/all/SortAnimation/sort/AnimationApplet.java
 * https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class SortGUI extends JApplet implements ActionListener
{

	ArrayList<Integer> arrList; 
	static final int FRAME_WIDTH = 1200;
	static final int FRAME_HEIGHT = 500;
	static int elements=15;            //Number of elements
	public static int delay=500;				//speed to sorting 

	private JLabel SelectionSort;
	private JLabel InsertionSort;
	private JLabel BubbleSort;	
	private JPanel labelPanel; // Panel consist of three JLabels

	private JPanel ArrayPanel; // Panel consist of three ArrayComponent Panel
	private ArrayComponent SelectionSortPanel;  
	private ArrayComponent InsertionSortPanel; 
	private ArrayComponent BubbleSortPanel;

	Integer[] selectionArray = new Integer[elements]; // array for selection sort
	Integer[] insertionArray = new Integer[elements]; // array for insertion sort
	Integer[] bubbleArray = new Integer[elements];    // array for bubble sort
	Integer[] values = new Integer[elements];
	/*
	 * Create GUi and add the elements
	 */
	public  void init(){
		// --------------------SORTING BAR panel --------------------
		ArrayPanel = new JPanel();
		ArrayPanel.setLayout(new GridLayout(1,3));

		//create and add arrayComonent to panels
		SelectionSortPanel = new ArrayComponent();
		InsertionSortPanel = new ArrayComponent();
		BubbleSortPanel = new ArrayComponent();

		ArrayPanel.add(SelectionSortPanel);
		ArrayPanel.add(InsertionSortPanel);
		ArrayPanel.add(BubbleSortPanel);

		this.add(ArrayPanel, BorderLayout.CENTER);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);

		// --------------------LABEL panel --------------------
		labelPanel = new JPanel();
		labelPanel.setBackground(Color.WHITE);
		labelPanel.setLayout(new GridLayout(1,3));

		SelectionSort = new JLabel("Selection Sort");
		InsertionSort = new JLabel("Insertion Sort");
		BubbleSort = new JLabel("Bubble Sort");
		SelectionSort.setFont(new Font("Serif", Font.PLAIN, 40));
		InsertionSort.setFont(new Font("Serif", Font.PLAIN, 40));
		BubbleSort.setFont(new Font("Serif", Font.PLAIN, 40));;

		labelPanel.add(SelectionSort);
		labelPanel.add(InsertionSort);
		labelPanel.add(BubbleSort);

		this.add(labelPanel,BorderLayout.NORTH);
	}

	/*
	 * Start the GUI app
	 */
	public void start(){

		arrList = new ArrayList<Integer>(); // create a arrList consist number 1-40
		for (int i = 1; i <= 50; i++) {
			arrList.add(i);
		}
		Collections.shuffle(arrList); // randomly shuffle the number
		
		for (int i = 0; i < elements; i++) { // take the certain number of the list to create the rectangle
			int n = arrList.get(i); 
			// create the same number list for three sorting
			selectionArray[i] = n; 
			insertionArray[i] = n;
			bubbleArray[i] = n;
			values[i] = n;
		}	

		// Create three threads
		Runnable r1 = new Sorter(SelectionSortPanel, selectionArray, "select", delay);
		Thread t1 = new Thread(r1);
		t1.start();


		Runnable r2 = new Sorter(InsertionSortPanel, insertionArray, "insert", delay);
		Thread t2=new Thread(r2);
		t2.start();	 

		Runnable r3 = new Sorter(BubbleSortPanel, bubbleArray, "bubble", delay);
		Thread t3=new Thread(r3);
		t3.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
