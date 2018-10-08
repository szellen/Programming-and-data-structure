

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
   *This program animates a sort algorithm.
  *@author tbahls
  *@author Horstman
  *@author JavaSwingTutorial
*/
public class AnimationTester implements ActionListener
{
	
	private String algorithm="heap";    //Algoritm to sort by
	private int elements=20;            //Number of elements to sort
	private int delay=20;				  //speed to sort at
	private ArrayComponent panel;       //The display Panel
	private Thread t;					 //A Thread for running sorts
	
	
	/**
	 * Creates an Animation Tester object and tells it to go.
	 * This lets everything happen.
	 * @param args The standard parameter
	 * 
	 */
   public static void main(String[] args)
   {
	   (new AnimationTester()).go();
   }
   
   
   
   /**
    * Launches the Sorting animation.
    * Creates a GUI
    */
   private void go(){
      JFrame frame = new JFrame("Sort Demo");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      panel = new ArrayComponent();
      frame.add(panel, BorderLayout.CENTER);
      
      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
     
      
      
//    Where the GUI is created:
      JMenuBar menu;
      JMenu sort,num,delay; 
     


//      Create the menu bar.
      menu = new JMenuBar();

//      Build the first menu.
      sort = new JMenu("Algorithm");
      sort.setMnemonic(KeyEvent.VK_A);
      sort.getAccessibleContext().setAccessibleDescription(
              "Choose an algorithm");

//      a group of radio button menu items
     
      
      JRadioButtonMenuItem bubble = new JRadioButtonMenuItem("Bubble Sort");
      bubble.setActionCommand("bubble");
      JRadioButtonMenuItem merge = new JRadioButtonMenuItem("Merge Sort");
      merge.setActionCommand("merge");
      JRadioButtonMenuItem heap = new JRadioButtonMenuItem("Heap Sort");
      heap.setActionCommand("heap");
      heap.setSelected(true);
      JRadioButtonMenuItem insert = new JRadioButtonMenuItem("Insertion Sort");
      insert.setActionCommand("insert");
      JRadioButtonMenuItem quick = new JRadioButtonMenuItem("Quick Sort");
      quick.setActionCommand("quick");
       JRadioButtonMenuItem shell = new JRadioButtonMenuItem("Shell Sort");
      shell.setActionCommand("shell");
      JRadioButtonMenuItem selection = new JRadioButtonMenuItem("Selection Sort");
      selection.setActionCommand("select");

      //Group the radio buttons.
      ButtonGroup group1 = new ButtonGroup();
      group1.add(bubble);
      group1.add(merge);
      group1.add(heap);
      group1.add(insert);
      group1.add(quick);
      group1.add(shell);
      group1.add(selection);
      sort.add(bubble);
      sort.add(merge);
      sort.add(heap);
      sort.add(insert);
      sort.add(quick);
      sort.add(shell);
      sort.add(selection);
     
      bubble.addActionListener(this);
      merge.addActionListener(this);
      heap.addActionListener(this);
      insert.addActionListener(this);
      quick.addActionListener(this);
      shell.addActionListener(this);
      selection.addActionListener(this);
      

//      Build second menu in the menu bar.
      num = new JMenu("Elements");
      num.setMnemonic(KeyEvent.VK_S);
      num.getAccessibleContext().setAccessibleDescription(
              "How many elements?");
      
//      a group of radio button menu items
     
      
      JRadioButtonMenuItem n10 = new JRadioButtonMenuItem("10");
      n10.setActionCommand("10");
      JRadioButtonMenuItem n20 = new JRadioButtonMenuItem("20");
      n20.setActionCommand("20");
      n20.setSelected(true);
      JRadioButtonMenuItem n30 = new JRadioButtonMenuItem("30");
      n30.setActionCommand("30");    
      JRadioButtonMenuItem n50 = new JRadioButtonMenuItem("50");
      n50.setActionCommand("50");
      JRadioButtonMenuItem n100 = new JRadioButtonMenuItem("100");
      n100.setActionCommand("100");

      //Group the radio buttons.
      ButtonGroup group2 = new ButtonGroup();
      group2.add(n10);
      group2.add(n20);
      group2.add(n30);
      group2.add(n50);
      group2.add(n100);
      num.add(n10);
      num.add(n20);
      num.add(n30);
      num.add(n50);
      num.add(n100);
     
      n10.addActionListener(this);
      n20.addActionListener(this);
      n30.addActionListener(this);
      n50.addActionListener(this);
      n100.addActionListener(this);
           
//    Build third menu in the menu bar.
    
      
      delay = new JMenu("Speed delay");
      delay.setMnemonic(KeyEvent.VK_S);
      delay.getAccessibleContext().setAccessibleDescription(
              "How slow?");
      
//      a group of radio button menu items
     
      
      JRadioButtonMenuItem s0 = new JRadioButtonMenuItem("0");
      s0.setActionCommand("s000");
      JRadioButtonMenuItem s20 = new JRadioButtonMenuItem("20");
      s20.setActionCommand("s020");
      s20.setSelected(true);
      JRadioButtonMenuItem s50 = new JRadioButtonMenuItem("50");
      s50.setActionCommand("s050");
      JRadioButtonMenuItem s100 = new JRadioButtonMenuItem("100");
      s100.setActionCommand("s100");
      JRadioButtonMenuItem s500 = new JRadioButtonMenuItem("500");
      s500.setActionCommand("s500");

      //Group the radio buttons.
      ButtonGroup group3 = new ButtonGroup();
      group3.add(s0);
      group3.add(s20);
      group3.add(s50);
      group3.add(s100);
      group3.add(s500);
      delay.add(s0);
      delay.add(s20);
      delay.add(s50);
      delay.add(s100);
      delay.add(s500);
     
      s0.addActionListener(this);
      s20.addActionListener(this);
      s50.addActionListener(this);
      s100.addActionListener(this);
      s500.addActionListener(this);
      
      
      //add the menus to the Menubar
      menu.add(sort);
      menu.add(num);
      menu.add(delay);
     
      frame.setJMenuBar(menu);

      frame.setVisible(true);
      
      
      startSort();
     
      
   }
   
   /**
    * Launches a sort.
    * If a sort is already in progress, does nothing.
    *
    */
   private void startSort(){
	   //do not sort if already busy sorting.
	   if(t!=null&&t.isAlive()) return;
	   Double[] values = new Double[elements];
	      for (int i = 0; i < values.length; i++)
	         values[i] = new Double(Math.random() * panel.getHeight()); 
	      Runnable r = new Sorter(panel, values, algorithm, delay);
	     t=new Thread(r);
	     t.start();
   }
   
  
   
   /**
    * Detects menu commands
    * @param e The event that occured
    */
   public synchronized void actionPerformed(ActionEvent e){
	   String s=e.getActionCommand();
	   if(s=="bubble"||
			   s=="insert"||
	                   s=="select"){
		   algorithm=s;
		   startSort();
	   }
	   if(s=="20"){
		   elements=Integer.parseInt(s);
		   startSort();
	   }
	   
	   if(s=="s500"){
		   delay=Integer.parseInt(s.substring(1,4));
	   }
	
	   
	 
   }
   
   //private static final int VALUES_LENGTH = 30;
   private static final int FRAME_WIDTH = 400;
   private static final int FRAME_HEIGHT = 400;
}
